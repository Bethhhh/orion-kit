package com.orion.generator.faker;

import com.orion.generator.addres.AddressGenerator;
import com.orion.generator.addres.AddressSupport;
import com.orion.generator.bank.BankCardGenerator;
import com.orion.generator.bank.BankCardType;
import com.orion.generator.bank.BankNameType;
import com.orion.generator.education.EducationGenerator;
import com.orion.generator.education.UniversityGenerator;
import com.orion.generator.email.EmailGenerator;
import com.orion.generator.idcard.IdCardGenerator;
import com.orion.generator.industry.IndustryGenerator;
import com.orion.generator.mobile.MobileGenerator;
import com.orion.generator.name.EnglishNameGenerator;
import com.orion.generator.name.NameGenerator;
import com.orion.generator.plate.LicensePlateGenerator;
import com.orion.lang.wrapper.Pair;
import com.orion.utils.Arrays1;
import com.orion.utils.random.Randoms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

/**
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2021/8/13 18:07
 */
public class Faker {

    private static final int AGE_MIN, AGE_MAX;

    private Faker() {
    }

    static {
        AGE_MIN = 18;
        AGE_MAX = 50;
    }

    /**
     * 生成假数据 [18 ~ 50] 岁
     *
     * @param types 类型
     * @return 假数据
     */
    public static FakerInfo generator(FakerType... types) {
        return generator(AGE_MIN, AGE_MAX, Randoms.randomBoolean(), types);
    }

    public static FakerInfo generator(int age, FakerType... types) {
        return generator(age, Randoms.randomBoolean(), types);
    }

    public static FakerInfo generator(boolean gender, FakerType... types) {
        return generator(AGE_MIN, AGE_MAX, gender, types);
    }

    public static FakerInfo generator(int ageMin, int ageMax, FakerType... types) {
        return generator(ageMin, ageMax, Randoms.randomBoolean(), types);
    }

    public static FakerInfo generator(int ageMin, int ageMax, boolean gender, FakerType... types) {
        return generator(Randoms.randomInt(ageMin, ageMax), gender, types);
    }

    /**
     * 生成假数据
     *
     * @param age    年龄
     * @param gender 性别
     * @param types  类型
     * @return 假数据
     */
    public static FakerInfo generator(int age, boolean gender, FakerType... types) {
        FakerInfo fakerInfo = new FakerInfo();
        fakerInfo.setAge(age);
        fakerInfo.setGender(gender);
        if (Arrays1.isEmpty(types)) {
            return fakerInfo;
        }
        Arrays.sort(types, Comparator.comparing(Enum::ordinal));
        for (FakerType type : types) {
            if (FakerType.NAME.equals(type)) {
                fakerInfo.setName(NameGenerator.generatorName(gender));
            } else if (FakerType.EN_NAME.equals(type)) {
                fakerInfo.setEnName(EnglishNameGenerator.generatorName());
            } else if (FakerType.MOBILE.equals(type)) {
                fakerInfo.setMobile(MobileGenerator.generateMobile());
            } else if (FakerType.EMAIL.equals(type)) {
                fakerInfo.setEmail(EmailGenerator.generatorEmail());
            } else if (FakerType.ADDRESS.equals(type)) {
                Integer provinceCode = AddressSupport.randomProvinceCode();
                Integer cityCode = AddressSupport.randomCityCode(provinceCode);
                Integer countyCode = AddressSupport.randomCountyCode(cityCode);
                String address = AddressSupport.getCountyAddress(countyCode);
                String[] addressExt = AddressSupport.getAddressExt(countyCode);
                String detailAddress = AddressGenerator.generatorAddress();
                fakerInfo.setProvinceCode(provinceCode);
                fakerInfo.setProvinceName(addressExt[0]);
                fakerInfo.setCityCode(cityCode);
                fakerInfo.setCityName(addressExt[1]);
                fakerInfo.setCountryCode(countyCode);
                fakerInfo.setCountryName(addressExt[2]);
                fakerInfo.setAddress(address);
                fakerInfo.setDetailAddress(detailAddress);
            } else if (FakerType.ID_CARD.equals(type)) {
                String idCard = IdCardGenerator.generator(age, gender);
                String fullAddress = IdCardGenerator.getFullAddress(idCard);
                String issueOrg = IdCardGenerator.getIssueOrg(idCard);
                String periodString = IdCardGenerator.getPeriodString(idCard);
                Date[] period = IdCardGenerator.getPeriod(idCard);
                Integer[] codeExt = IdCardGenerator.getAddressCodeExt(idCard);
                String[] addressExt = IdCardGenerator.getAddressExt(idCard);
                fakerInfo.setIdCardNo(idCard);
                fakerInfo.setIdCardAddress(fullAddress);
                fakerInfo.setIdCardIssueOrg(issueOrg);
                fakerInfo.setIdCardPeriod(periodString);
                fakerInfo.setIdCardPeriodStart(period[0]);
                fakerInfo.setIdCardPeriodEnd(period[1]);
                fakerInfo.setIdCardProvinceCode(codeExt[0]);
                fakerInfo.setIdCardProvinceName(addressExt[0]);
                fakerInfo.setIdCardCityCode(codeExt[1]);
                fakerInfo.setIdCardCityName(addressExt[1]);
                fakerInfo.setIdCardCountryCode(codeExt[2]);
                fakerInfo.setIdCardCountryName(addressExt[2]);
            } else if (FakerType.DEBIT_CARD.equals(type)) {
                Pair<BankNameType, String> bankPair = BankCardGenerator.generatorCard(BankCardType.DEBIT);
                fakerInfo.setDebitCardNo(bankPair.getValue());
                fakerInfo.setDebitBankCode(bankPair.getKey().getCode());
                fakerInfo.setDebitBankName(bankPair.getKey().getName());
            } else if (FakerType.CREDIT_CARD.equals(type)) {
                Pair<BankNameType, String> bankPair = BankCardGenerator.generatorCard(BankCardType.CREDIT);
                fakerInfo.setCreditCardNo(bankPair.getValue());
                fakerInfo.setCreditBankCode(bankPair.getKey().getCode());
                fakerInfo.setCreditBankName(bankPair.getKey().getName());
            } else if (FakerType.EDUCATION.equals(type)) {
                String education = EducationGenerator.generatorEducation(age);
                fakerInfo.setEducation(education);
            } else if (FakerType.UNIVERSITY.equals(type)) {
                String university = Optional.ofNullable(fakerInfo.getEducation())
                        .map(UniversityGenerator::generatorUniversity)
                        .orElseGet(UniversityGenerator::generatorUniversity);
                fakerInfo.setUniversity(university);
            } else if (FakerType.INDUSTRY.equals(type)) {
                String industry = IndustryGenerator.generatorIndustry(age);
                fakerInfo.setIndustry(industry);
            } else if (FakerType.LICENSE_PLATE.equals(type)) {
                String licensePlate;
                if (fakerInfo.getIdCardProvinceCode() != null) {
                    licensePlate = LicensePlateGenerator.generator(fakerInfo.getIdCardProvinceCode());
                } else if (fakerInfo.getProvinceCode() != null) {
                    licensePlate = LicensePlateGenerator.generator(fakerInfo.getProvinceCode());
                } else {
                    licensePlate = LicensePlateGenerator.generator();
                }
                fakerInfo.setLicensePlate(licensePlate);
            }
        }
        return fakerInfo;
    }

}
