package hu.projekt.bolt.mapper;

import hu.projekt.bolt.dto.NapIdopontDTO;
import hu.projekt.bolt.dto.TevekenysegDTO;
import hu.projekt.bolt.dto.TevekenysegNaploDTO;
import hu.projekt.bolt.model.Tevekenyseg;
import hu.projekt.bolt.model.TevekenysegGyakorisag;
import hu.projekt.bolt.model.TevekenysegIdopont;
import hu.projekt.bolt.model.TevekenysegNaplo;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper(componentModel = "spring")
public interface TevekenysegMapper {

    TevekenysegNaploDTO TevekenysegNaploToDTO(TevekenysegNaplo tevekenysegNaplo);


    @Mapping(source = "tevekenyseg.megnevezes", target = "megnevezes")
    @Mapping(source = "tevekenyseg.leiras", target = "leiras")
    @Mapping(source = "tevekenysegGyakorisag.gyakorisag", target = "gyakorisag")
    @Mapping(source = "tevekenysegGyakorisag.kezdoDatum", target = "kezdoDatum")
    @Mapping(source = "tevekenysegGyakorisag.idopontok", target = "idopontok")
    TevekenysegDTO toTevekenysegDTO(Tevekenyseg tevekenyseg, TevekenysegGyakorisag tevekenysegGyakorisag);

    @IterableMapping(qualifiedByName = "idopontToDto")
    List<NapIdopontDTO> mapIdopontokToDTOs(List<TevekenysegIdopont> idopontok);

    @Named("idopontToDto")
    default NapIdopontDTO idopontToDto(TevekenysegIdopont idopont) {
        if (idopont == null) return null;
        return new NapIdopontDTO(idopont.getNap(), idopont.getIdopont());
    }

    // Ha később DTO-ból vissza akarsz mappelni entitásba:
    @Named("dtoToIdopont")
    default TevekenysegIdopont dtoToIdopont(NapIdopontDTO dto) {
        TevekenysegIdopont idopont = new TevekenysegIdopont();
        idopont.setNap(dto.getNap());
        idopont.setIdopont(dto.getIdopont());
        return idopont;
    }

    @IterableMapping(qualifiedByName = "dtoToIdopont")
    List<TevekenysegIdopont> mapDTOsToIdopontok(List<NapIdopontDTO> dtos);
}
