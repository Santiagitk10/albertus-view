package com.sofka.albertusview.business.gateways;

import com.sofka.albertusview.business.gateways.models.BlockViewModel;

public interface EventBus {

    void publishApplicationCreated(PostViewModel postViewModel);
    void publishBlockCreated(BlockViewModel blockViewModel);


}
