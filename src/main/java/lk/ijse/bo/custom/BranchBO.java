package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BranchDto;

import java.util.List;

public interface BranchBO extends SuperBO {
    List<BranchDto> getAll() throws Exception;

    boolean save(BranchDto branchDto) throws Exception;

    boolean update(BranchDto branchDto) throws Exception;

    void delete(int id) throws Exception;
}
