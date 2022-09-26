package com.sofka.albertusview.business.gateways;


import com.mongodb.client.result.UpdateResult;
import com.sofka.albertusview.business.gateways.models.ApplicationViewModel;
import com.sofka.albertusview.business.gateways.models.BlockChainModel;

import com.sofka.albertusview.business.gateways.models.BlockViewModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainViewRepository {
    Mono<BlockChainModel> findByAggregateId(String aggregateId);
    Mono<BlockChainModel> saveNewBlockChain(BlockChainModel blockChainModel);

    Mono<BlockChainModel> addBlockToBlockChain(BlockViewModel block);

    Mono<BlockViewModel> addBlock(BlockViewModel block);

    Mono<BlockViewModel> getBlockByHash(String hash);

    Flux<BlockViewModel> getAllBlocksByApplicationId(String applicationId);

    Mono<ApplicationViewModel> saveNewApplication(ApplicationViewModel application);

    Mono<UpdateResult> updateDeleteApplication(String idApplication);


<<<<<<< HEAD
    Mono<UpdateResult> updateApplication(String idApplication, String name, String description);



=======
    Mono<UpdateResult> updateApplication(String idApplication, String description, String name);
>>>>>>> 8efd71b2391a035d91fbc5907197bc1c6ee7c03e
}
