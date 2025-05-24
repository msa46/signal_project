package com.data_management;

import java.io.IOException;

/**
 * Interface for reading data from a real-time source such as a WebSocket server.
 */
public interface DataReader {
    /**
     * Connects to a WebSocket server and continuously receives data,
     * storing it in the provided data storage.
     *
     * @param dataStorage the storage where received data will be stored
     * @param serverUri the URI of the WebSocket server to connect to
     * @throws IOException if there is an error connecting or receiving data
     */
    void connectAndStream(DataStorage dataStorage, String serverUri) throws IOException;

    /**
     * Stops receiving data and closes the WebSocket connection.
     *
     * @throws IOException if there is an error closing the connection
     */
    void disconnect() throws IOException;
}