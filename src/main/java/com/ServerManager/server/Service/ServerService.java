package com.ServerManager.server.Service;

import com.ServerManager.server.model.Server;

import java.util.Collection;

public interface ServerService {

    Server createServer(Server server);
    Server ping(String ipAddress);
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update (Server update);
    Boolean delete(Long id);
}
