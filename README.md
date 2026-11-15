# valorant-tracker-backend

Backend cho app cá nhân theo dõi chỉ số Valorant. Java 21, Spring Boot 3.5, Maven, PostgreSQL, Flyway, Caffeine cache.
Dữ liệu lấy từ API cộng đồng HenrikDev (không chính thức, chỉ dùng cho dự án cá nhân).

## Chạy local

Cần: JDK 21, Maven 3.9+, Docker.

```bash
docker compose up -d                 # Postgres ở localhost:5432
export HENRIK_API_KEY=<key của bạn>  # lấy ở Discord HenrikDev, kênh #get-a-key
mvn spring-boot:run
```

- Swagger UI: http://localhost:8080/swagger-ui.html
- Health: http://localhost:8080/actuator/health

Chạy test (dùng H2, không cần Postgres): `mvn test`

## API hiện có

| Method | Path | Mô tả |
| --- | --- | --- |
| GET | `/api/v1/players/{name}/{tag}` | Hồ sơ người chơi, gọi HenrikDev, cache 5 phút |
| GET | `/api/v1/players/{name}/{tag}/matches?limit=20` | Các trận đã lưu trong DB (chưa có bước đồng bộ) |

## Cấu trúc

Chia theo tính năng, mỗi tính năng có đủ các tầng Clean Architecture.
Trong một tính năng, phụ thuộc chỉ hướng vào trong: presentation → application → domain ← infrastructure.

```
src/main/java/dev/luisnv/tracker
├── player/
│   ├── domain/           Player, PlayerGateway (port). Java thuần
│   ├── application/      GetPlayerProfileUseCase
│   ├── infrastructure/   HenrikPlayerGateway (có cache)
│   └── presentation/     PlayerController, PlayerProfileResponse
├── match/
│   ├── domain/           Match, MatchRepository (port). Java thuần
│   ├── application/      GetRecentMatchesUseCase
│   ├── infrastructure/   MatchJpaEntity, MatchJpaRepository, JpaMatchRepository
│   └── presentation/     MatchController, MatchSummaryResponse
└── shared/           Dùng chung giữa các tính năng
    ├── domain/exception/        NotFoundException, StatsProviderException
    ├── infrastructure/henrik/   HenrikClient và cấu hình
    └── presentation/            GlobalExceptionHandler
src/main/resources
├── application.yml
└── db/migration/V1__create_matches.sql
```

Quy ước giữa các tính năng: một tính năng chỉ được dùng tầng `domain` của tính năng khác (ví dụ `match` dùng `player.domain.PlayerGateway`), không dùng `infrastructure` hay `presentation` của nhau.
Thêm tính năng mới (ví dụ `stats`): tạo package cùng cấp với đủ 4 tầng như trên.

Use case dùng `@Service` của Spring cho gọn. Nếu muốn tầng application sạch hoàn toàn, bỏ annotation và khai báo bean trong một lớp `@Configuration`.

## Việc cần làm tiếp

1. `HenrikClient`: thêm `getMatches` và `getMmr` (xem đường dẫn và tên trường ở https://docs.henrikdev.xyz).
2. `SyncMatchesUseCase` + port `MatchGateway`: kéo trận mới, bỏ qua trận đã lưu, ghi vào bảng `matches`.
3. Endpoint `/stats`: tính K/D, ACS, tỉ lệ thắng theo agent và map từ bảng `matches`.
4. Giới hạn tốc độ gọi ra ngoài (key Basic: 30 request/phút), ví dụ bằng Bucket4j.
