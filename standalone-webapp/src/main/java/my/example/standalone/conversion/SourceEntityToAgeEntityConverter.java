package my.example.standalone.conversion;

import org.springframework.core.convert.converter.Converter;

public class SourceEntityToAgeEntityConverter implements Converter<SourceEntity, AgeEntity> {

    @Override
    public AgeEntity convert(SourceEntity source) {
        AgeEntity ageEntity = new AgeEntity();
        ageEntity.setAge(Integer.valueOf(source.getAge()));

        return ageEntity;
    }
}
