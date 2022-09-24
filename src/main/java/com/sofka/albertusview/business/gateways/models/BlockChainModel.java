package com.sofka.albertusview.business.gateways.models;

import com.sofka.albertusview.domain.entity.Application;
import com.sofka.albertusview.domain.entity.Invoice;
import com.sofka.albertusview.domain.values.Block;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.html.BlockView;

public class BlockChainModel {

  private String blockChainId;
  private String blockChainName;
  private List<BlockViewModel> blocks;
  private List<Application> applications;
  private List<Invoice> invoices;

  public BlockChainModel(String blockChainId, String blockChainName, List<BlockViewModel> blocks,
      List<Application> applications, List<Invoice> invoices) {
    this.blockChainId = blockChainId;
    this.blockChainName = blockChainName;
    this.blocks = blocks;
    this.applications = applications;
    this.invoices = invoices;
  }



  public String getBlockChainId() {
    return blockChainId;
  }

  public void setBlockChainId(String blockChainId) {
    this.blockChainId = blockChainId;
  }

  public String getBlockChainName() {
    return blockChainName;
  }

  public void setBlockChainName(String blockChainName) {
    this.blockChainName = blockChainName;
  }

  public List<BlockViewModel> getBlocks() {
    return blocks;
  }

  public void setBlocks(
      List<BlockViewModel> blocks) {
    this.blocks = blocks;
  }

  public List<Application> getApplications() {
    return applications;
  }

  public void setApplications(
      List<Application> applications) {
    this.applications = applications;
  }

  public List<Invoice> getInvoices() {
    return invoices;
  }

  public void setInvoices(List<Invoice> invoices) {
    this.invoices = invoices;
  }
}
