package entity;

import com.bxg.pyg.pojo.TbSpecification;
import com.bxg.pyg.pojo.TbSpecificationOption;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

public class SpecificationResult implements Serializable {


    public TbSpecification getSpecification() {
        return specification;
    }

    public void setSpecification(TbSpecification specification) {
        this.specification = specification;
    }
    private TbSpecification specification;




    public List<TbSpecificationOption> getSpecificationOptionList() {

        return specificationOptionList;
    }

    public void setSpecificationOptionList(List<TbSpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }

    private List<TbSpecificationOption> specificationOptionList;



}