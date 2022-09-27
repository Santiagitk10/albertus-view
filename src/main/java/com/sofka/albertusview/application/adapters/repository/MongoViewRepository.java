package com.sofka.albertusview.application.adapters.repository;


import com.google.gson.Gson;
import com.mongodb.client.result.UpdateResult;
import com.sofka.albertusview.business.gateways.DomainViewRepository;
import com.sofka.albertusview.business.gateways.EventBus;
import com.sofka.albertusview.business.gateways.models.ApplicationViewModel;
import com.sofka.albertusview.business.gateways.models.BlockChainModel;
import com.sofka.albertusview.business.gateways.models.BlockViewModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Slf4j
@Repository
@Component
public class MongoViewRepository implements DomainViewRepository {

    private final ReactiveMongoTemplate template;

    private final Gson gson = new Gson();

    private final EventBus bus;

    public MongoViewRepository(ReactiveMongoTemplate template, EventBus bus) {
        this.template = template;
        this.bus = bus;
    }

    @Override
    public Mono<BlockChainModel> findByAggregateId(String aggregateId) {

        var query = new Query(Criteria.where("blockChainId").is(aggregateId));
        return template.findOne(query, BlockChainModel.class);
    }


    @Override
    public Mono<BlockChainModel> saveNewBlockChain(BlockChainModel blockChainModel) {

        return template.save(blockChainModel);
    }

    @Override
    public Mono<BlockChainModel> addBlockToBlockChain(BlockViewModel block) {
    var update = new Update();
    update.addToSet("blocks").value(block);
    var query =  Query.query(
            Criteria.where("blockChainId").is("1")
    );
    return template.updateFirst(query,update,"blockChainModel").flatMap(block2 ->{
        return Mono.empty();
    });


       /* var algo = this.findByAggregateId("1")
                .flatMap(blockChainModel -> {
                    blockChainModel.getBlocks().add(block);

                    return template.save(blockChainModel);
                });
        return algo;*/
    }

    @Override
    public Mono<BlockViewModel> addBlock(BlockViewModel block) {
        return template.save(block);
    }

    @Override
    public Mono<BlockViewModel> getBlockByHash(String hash) {
        var query =  Query.query(
                Criteria.where("hash").is(hash)
        );
        return template.findOne(query,BlockViewModel.class);
    }


    public Flux<BlockViewModel> getAllBlocksByApplicationId(String idApplication) {
        var query =  Query.query(
                Criteria.where("applicationId").is(idApplication)
        );
        return template.find(query,BlockViewModel.class);
    }

    @Override
    public Mono<ApplicationViewModel> saveNewApplication(ApplicationViewModel application) {
        bus.publishApplication(application);
        return template.save(application);
    }

    @Override
    public Mono<UpdateResult> updateDeleteApplication(String idApplication) {
        var data = new Update();
        data.set("isActive", false);
        var query =  Query.query(
                Criteria.where("applicationID").is(idApplication)
        );
        return template.updateFirst(query, data, ApplicationViewModel.class);
    }
    @Override
    public Mono<UpdateResult> updateApplication(String idApplication, String description, String name) {
        var data = new Update();
        data.set("description", description);
        data.set("nameApplication", name);
        data.set("modificationDate", Instant.now());

        var query =  Query.query(
                Criteria.where("applicationID").is(idApplication)
        );
        return template.updateFirst(query, data, ApplicationViewModel.class);
    }

    public Flux<ApplicationViewModel> getAllApplicationsByUserId(String userId){
        var query =  Query.query(
                Criteria.where("userId").is(userId)
        );
        return template.find(query,ApplicationViewModel.class);
    }


}
