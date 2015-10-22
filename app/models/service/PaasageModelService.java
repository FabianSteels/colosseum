package models.service;

import com.google.inject.Inject;
import models.PaasageModel;

/**
 * Created by ec on 9/02/15.
 */
public class PaasageModelService extends BaseModelService<PaasageModel> implements PaasageModelServiceInterface  {

    @Inject
    public PaasageModelService(PaasageModelRepository paasageModelRepository) {
        super(paasageModelRepository);
    }
}
