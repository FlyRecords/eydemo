package com.eydemo.demo.transformers;

import com.eydemo.demo.entity.Phone;
import com.eydemo.demo.vo.PhoneVO;

import java.util.ArrayList;
import java.util.List;

public class PhoneTransformer {

    public static Phone toModel(PhoneVO phoneVO) {
        Phone phone = new Phone();
        phone.setId(phoneVO.getId());
        phone.setNumber(phoneVO.getNumber() != null ? phoneVO.getNumber() : null);
        phone.setCityCode(phoneVO.getCytycode() != null ? phoneVO.getCytycode() : null);
        phone.setCountryCode(phoneVO.getCountrycode() != null ? phoneVO.getCountrycode() : null);
        return phone;
    }

    public static PhoneVO toVO(Phone phone) {
        PhoneVO phoneVO = new PhoneVO();
        phoneVO.setId(phone.getId());
        phoneVO.setNumber(phone.getNumber() != null ? phone.getNumber() : null);
        phoneVO.setCytycode(phone.getCityCode() != null ? phone.getCityCode() : null);
        phoneVO.setCountrycode(phone.getCountryCode() != null ? phone.getCountryCode() : null);
        return phoneVO;
    }

    public static List<PhoneVO> phoneVOList(List<Phone> phoneList){
        List<PhoneVO> phoneVOList = new ArrayList<>();
        if(phoneList != null || !phoneList.isEmpty()){
            for (Phone phone : phoneList){
                PhoneVO vo = toVO(phone);
                phoneVOList.add(vo);
            }
        }
        return phoneVOList;
    }

    public static List<Phone> phoneList(List<PhoneVO> phoneVOList){
        List<Phone> phoneList = new ArrayList<>();
        if(phoneVOList != null || !phoneVOList.isEmpty()){
            for (PhoneVO vo : phoneVOList){
                Phone model = toModel(vo);
                phoneList.add(model);
            }
        }
        return phoneList;
    }


}
