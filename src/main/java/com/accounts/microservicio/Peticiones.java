package com.accounts.microservicio;

import com.accounts.pedido.modelos.Pedido;
import com.accounts.pedido.modelos.PedidoItem;
import org.springframework.stereotype.Controller;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Controller
public class Peticiones {

    public void sendPedidoToExternalService(Pedido pedido) {


        try {
            HttpClient client = HttpClient.newHttpClient();

            for (PedidoItem item : pedido.getOrderItems()) {
                System.out.println("pedidoactualizado"+ pedido.getActualizadoen());
                String params = "descripcion=" + URLEncoder.encode(item.getDescripcion(), StandardCharsets.UTF_8)
                        + "&cantidad=" + URLEncoder.encode(String.valueOf(item.getQuantity()), StandardCharsets.UTF_8)
                        + "&precio=" + URLEncoder.encode(String.valueOf(item.getPrice()), StandardCharsets.UTF_8)
                        + "&actualizadoen=" + URLEncoder.encode(pedido.getActualizadoen(), StandardCharsets.UTF_8)
                        + "&pedidoid=" + URLEncoder.encode(pedido.getId(), StandardCharsets.UTF_8);

                System.out.println("Params: " + params);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:5000/pedido"))
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .POST(HttpRequest.BodyPublishers.ofString(params))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("Response: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error making the HTTP request");
        }
    }






}