package com.cardio_generator.outputs;

import com.data_management.DataStorage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;

public class WebSocketClient {
    private WebSocket webSocket;
    private final DataStorage dataStorage;
    private final CountDownLatch closeLatch = new CountDownLatch(1);

    public WebSocketClient(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public void connect(String serverUri) {
        HttpClient client = HttpClient.newHttpClient();
        client.newWebSocketBuilder()
                .buildAsync(URI.create(serverUri), new WebSocketListener())
                .thenAccept(ws -> this.webSocket = ws)
                .exceptionally(ex -> {
                    System.err.println("Failed to connect to WebSocket server: " + ex.getMessage());
                    return null;
                });
    }

    public void disconnect() {
        if (webSocket != null) {
            webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "Disconnecting").thenRun(closeLatch::countDown);
        }
    }

    private class WebSocketListener implements Listener {
        @Override
        public void onOpen(WebSocket webSocket) {
            System.out.println("Connected to WebSocket server.");
            Listener.super.onOpen(webSocket);
        }

        @Override
        public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
            String message = data.toString();
            try {
                // Basic validation: check if message is not empty and is valid JSON
                if (message == null || message.trim().isEmpty()) {
                    System.err.println("Received empty message, ignoring.");
                } else if (!message.trim().startsWith("{")) {
                    System.err.println("Received malformed message: " + message);
                } else {
                    // Store the message (parsing can be added as needed)
                    dataStorage.storeData(message);
                }
            } catch (Exception e) {
                System.err.println("Error processing incoming message: " + e.getMessage());
            }
            return Listener.super.onText(webSocket, data, last);
        }

        @Override
        public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
            System.out.println("WebSocket connection closed: " + reason);
            closeLatch.countDown();
            return Listener.super.onClose(webSocket, statusCode, reason);
        }

        @Override
        public void onError(WebSocket webSocket, Throwable error) {
            System.err.println("WebSocket error: " + error.getMessage());
            error.printStackTrace();
        }
    }
}