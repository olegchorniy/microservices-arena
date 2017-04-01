package my.example.standalone.conversion;

import org.springframework.core.convert.converter.Converter;

public class SourceToTargetConverter implements Converter<SourceEntity, TargetEntity> {
    @Override
    public TargetEntity convert(SourceEntity source) {
        TargetEntity targetEntity = new TargetEntity();
        targetEntity.setAge(Integer.valueOf(source.getAge()));

        return targetEntity;
    }
}
