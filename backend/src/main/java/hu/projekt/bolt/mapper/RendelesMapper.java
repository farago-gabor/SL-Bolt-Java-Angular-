package hu.projekt.bolt.mapper;

import hu.projekt.bolt.dto.RendelesDTO;
import hu.projekt.bolt.dto.RendelesTetelDTO;
import hu.projekt.bolt.model.Rendeles;
import hu.projekt.bolt.model.RendelesTetel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper (componentModel = "spring")
public interface RendelesMapper {

    @Mapping(source = "felvetteDolgozo.nev", target = "dolgozoNev")
    RendelesDTO rendelesToDto(Rendeles r);

    Rendeles rendelesDtoToEntity(RendelesDTO rDto);

    List<RendelesTetelDTO> rendelesTetelListaToDto(List<RendelesTetel> r);

    @Mapping(source = "arucikk.megnevezes", target = "arucikkNev")
    RendelesTetelDTO RendelesTetelToDto(RendelesTetel r);
//    RendelesTetel rendelesTetelDtoToEntity(RendelesTetelDTO rDto);
}
