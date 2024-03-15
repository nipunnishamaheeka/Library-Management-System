package lk.ijse.controller.admin;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BranchBO;

public class BranchManagementFormController {
    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);


}
