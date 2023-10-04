package com.ServerManager.server.Service.Implementation;

import com.ServerManager.server.Enums.Status;
import com.ServerManager.server.Service.ServerService;
import com.ServerManager.server.model.Server;
import com.ServerManager.server.repo.ServerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.InetAddress;
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
        log.info("Pinging new Server {}",ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            server.setStatus(inetAddress.isReachable(5000)? Status.SERVER_UP: Status.SERVER_DOWN);
        } catch(Exception e)
        {
            log.info("Error pinging server {}",e.getMessage());
        }
        return serverRepo.save(server);
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("fetching servers :{}",limit);
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("feteching server by id {} :", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating Server: {}",server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("deleting Server by id: {}",id);
        serverRepo.deleteById(id);
        return Boolean.TRUE;
    }
    private String setServerImageUrl() {
        return null;
    }
}
