package hu.projekt.bolt.mapper;

import hu.projekt.bolt.dto.DolgozoDTO;
import hu.projekt.bolt.model.Dolgozo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring") // Ezzel lehet @Autowire-t használni
public interface DolgozoMapper {
    DolgozoDTO toDto(Dolgozo d);
    Dolgozo toEntity(DolgozoDTO dDto);
}
