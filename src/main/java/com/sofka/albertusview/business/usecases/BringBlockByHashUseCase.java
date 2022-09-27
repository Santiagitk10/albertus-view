package com.sofka.albertusview.business.usecases;

import com.sofka.albertusview.application.adapters.repository.MongoViewRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;
@Service
public class BringBlockByHashUseCase implements Function<String, Mono<Object>> {

    public  final MongoViewRepository repository;

    public BringBlockByHashUseCase(MongoViewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Object> apply(String hash) {

        var block = this.repository.getBlockByHash(hash);
        return block.flatMapMany(bloc ->   {
            System.out.println("");
            return Flux.just(bloc.getData());
        });



    }


}
