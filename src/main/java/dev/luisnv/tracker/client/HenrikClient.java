package dev.luisnv.tracker.client;

import com.fasterxml.jackson.databind.JsonNode;
import dev.luisnv.tracker.common.NotFoundException;
import dev.luisnv.tracker.common.UpstreamException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * Thin wrapper around the unofficial HenrikDev Valorant API.
 * Returns the "data" node of each response; mapping to DTOs happens in the services.
 * Endpoint paths and response fields: https://docs.henrikdev.xyz
 */
@Component
public class HenrikClient {

    private final RestClient restClient;

    public HenrikClient(RestClient henrikRestClient) {
        this.restClient = henrikRestClient;
    }

    public JsonNode getAccount(String name, String tag) {
        return get("/valorant/v2/account/{name}/{tag}", name, tag);
    }

    // TODO: add getMatches(region, name, tag) and getMmr(region, name, tag) once you have
    // picked the endpoint versions from the docs.

    private JsonNode get(String path, Object... uriVariables) {
        JsonNode body = restClient.get()
                .uri(path, uriVariables)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    int status = response.getStatusCode().value();
                    if (status == 404) {
                        throw new NotFoundException("Player not found");
                    }
                    throw new UpstreamException(status, "Stats provider returned HTTP " + status);
                })
                .body(JsonNode.class);
        if (body == null || body.path("data").isMissingNode()) {
            throw new UpstreamException(502, "Stats provider returned an empty response");
        }
        return body.path("data");
    }
}
