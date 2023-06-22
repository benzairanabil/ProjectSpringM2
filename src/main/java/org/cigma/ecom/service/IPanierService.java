package org.cigma.ecom.service;

import org.cigma.ecom.dto.PanierDto;
import org.cigma.ecom.model.Panier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPanierService {
	PanierDto insertPanier(PanierDto p, String username);
	PanierDto updatePanier(PanierDto p);
    void deletePanier(int id, String username);
    PanierDto selectOne(int id, String username);
    List<PanierDto> selectAll(String username);
    public Page<PanierDto> getPage(Pageable p, String username);
}
