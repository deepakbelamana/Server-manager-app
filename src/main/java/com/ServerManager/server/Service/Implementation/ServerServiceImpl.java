package com.ServerManager.server.Service.Implementation;

import com.ServerManager.server.Service.ServerService;
import com.ServerManager.server.model.Server;
import com.ServerManager.server.repo.ServerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class ServerServiceImpl implements ServerService {
    private final ServerRepo serverRepo;
    @Override
    public Server createServer(Server server) {
        log.info("saving new Server: {}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }



    @Override
    public Server ping(String ipAddress) {
        return null;
    }

    @Override
    public Collection<Server> list(int limit) {
        return null;
    }

    @Override
    public Server get(Long id) {
        return null;
    }

    @Override
    public Server update(Server update) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
    private String setServerImageUrl() {
        return null;
    }
}
