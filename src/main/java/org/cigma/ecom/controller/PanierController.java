package org.cigma.ecom.controller;

import org.cigma.ecom.dto.PanierDto;
import org.cigma.ecom.service.ExcelExporterService;
import org.cigma.ecom.service.IPanierService;
import org.cigma.ecom.util.CheckUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/panier")
@PreAuthorize("hasRole('ROLE_USER')")
public class PanierController {
    @Autowired
    IPanierService service;
    @Autowired
    CheckUser checkUser;
    ModelMapper modelMapper = new ModelMapper();
    @GetMapping(params = {"pageNumber", "pageSize"})
    public Page<PanierDto> getPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        Pageable pageParams = PageRequest.of(pageNumber, pageSize);
        return service.getPage(pageParams, username);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PanierDto getOne(@PathVariable("id") int id, @RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        PanierDto p = service.selectOne(id, username);
        return p;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PanierDto> getAll(@RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        return service.selectAll(username);
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getCount(@RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        return service.selectAll(username).stream().count();
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public PanierDto add(@RequestBody PanierDto p, @RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        p = service.insertPanier(p, username);
        return p;
    }

    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PanierDto update(@RequestBody PanierDto p, @RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        p = service.updatePanier(p);
        return p;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteOne(@PathVariable("id") int id, @RequestHeader("Authorization") String auth) {
        String username = checkUser.getUsername(auth);
        service.deletePanier(id, username);
    }

    @GetMapping(path = "/{id}/export")
    public void exportToExcel(HttpServletResponse response, @RequestHeader("Authorization") String auth) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=panier_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<PanierDto> listPaniers = service.selectAll(checkUser.getUsername(auth));

        ExcelExporterService excelExporter = new ExcelExporterService(listPaniers);

        excelExporter.export(response);
    }
}
