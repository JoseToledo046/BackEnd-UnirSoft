package com.unir.buscador.service;

import com.unir.buscador.model.pojo.Empresa;
import com.unir.buscador.model.pojo.EmpresaDto;
import com.unir.buscador.model.request.CreateEmpresaRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface EmpresaService {
    
    List<Empresa> getEmpresaL(
            Integer id,
            Integer id_film,
            Integer id_prod,
            String logo_path,
            String name);

    Empresa getEmpresa(Integer id);

    Empresa createEmpresa(CreateEmpresaRequest request);

    Boolean removeEmpresa(Integer id);

    Empresa updateEmpresaP(Integer id, String updRequest);

    Empresa updateEmpresa(Integer id, EmpresaDto updRequest);
    
}
