package com.sofka.albertusview.business.gateways;

import com.sofka.albertusview.business.gateways.models.ApplicationViewModel;
import com.sofka.albertusview.business.gateways.models.BlockChainModel;

public interface EventBus {

    void publishApplication(Object model);


}
