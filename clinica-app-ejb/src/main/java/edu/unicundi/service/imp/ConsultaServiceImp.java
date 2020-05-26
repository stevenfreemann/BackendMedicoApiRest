/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.imp;

import edu.unicundi.dto.ConsultaDto;
import edu.unicundi.dto.ConsultaExamenDto;
import edu.unicundi.dto.ConsultaExamenListaDto;
import edu.unicundi.dto.DetalleConsultaDto;
import edu.unicundi.dto.ExamenDto;
import edu.unicundi.entity.Consulta;
import edu.unicundi.entity.DetalleConsulta;
import edu.unicundi.entity.Token;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.repo.IConsultaRepo;
import edu.unicundi.repo.ILoginRepo;
import edu.unicundi.service.IConsultaExamenService;
import edu.unicundi.service.IConsultaService;
import edu.unicundi.service.ILoginService;
import edu.unicundi.view.ConsultaView;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import static javax.crypto.Cipher.SECRET_KEY;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.json.JsonObject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.xml.bind.DatatypeConverter;
import org.modelmapper.ModelMapper;

/**
 *
 * @author ASUS-PC
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ConsultaServiceImp implements IConsultaService {
    @EJB
    private ILoginRepo login;

    @EJB
    private IConsultaRepo repo;

    @EJB
    private IConsultaExamenService service;
    
    @Resource
    private SessionContext context;

    @Override
    public List<Consulta> listarTodos() {
        return repo.listarTodos();
    }

    @Override
    public Consulta listar(int id) throws NotFoundModelException {
        Consulta consulta = repo.listar(id);
        if (consulta == null) {
            throw new NotFoundModelException("Objeto consulta no encontrado");
        }

        Consulta consultaAux = new Consulta();
        consultaAux.setId(consulta.getId());
        for (DetalleConsulta dc : consulta.getDetalleConsulta()) {
            dc.setConsulta(consultaAux);
        }
        return consulta;
    }

    @Override
    public void guardar(Consulta consulta) {
        if (consulta.getDetalleConsulta() != null) {
            for (DetalleConsulta dc : consulta.getDetalleConsulta()) {
                dc.setConsulta(consulta);
            }
        }
        repo.guardar(consulta);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void guardarTransaccion(ConsultaExamenListaDto dto)throws NotFoundModelException {
        Claims claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary("firma"))
            .parseClaimsJws(dto.getToken()).getBody();
        Date expiracion=claims.getExpiration();
        Long horaActual = System.currentTimeMillis();
        Date ha = new Date(horaActual); 
        boolean aprobado = false;
        List<Token> tokens = login.listarTokens();
        for(Token t: tokens){
            if(t.getToken().equals(dto.getToken()) && ha.before(expiracion)){
                aprobado = true;
            }
        }
        if(aprobado==true){
            Integer idConsulta = null;
            try {
            ModelMapper modelMapper = new ModelMapper();
            Consulta consulta = modelMapper.map(dto.getConsulta(), Consulta.class);
            if (consulta.getDetalleConsulta() != null) {
                for (DetalleConsulta dc : consulta.getDetalleConsulta()) {
                    dc.setConsulta(consulta);
                }
            }
            repo.guardar(consulta);            
            idConsulta = repo.obtenerUltimoIdPorMedico(consulta.getMedico().getNombreMedico());            
            for (ExamenDto examen : dto.getListaExamen()) {
                ConsultaExamenDto ceDto = new ConsultaExamenDto();
                ceDto.setIdConsulta(idConsulta);
                ceDto.setIdExamen(examen.getIdExamen());
                ceDto.setInfoAdicional(examen.getInfoAdicional());
                service.guardar(ceDto);
            }
        } catch(Exception ex) {
            context.setRollbackOnly();
            throw new NotFoundModelException("Objeto no encontrado");
       }
        }else{
            throw new NotFoundModelException("Sesion no valida");
        }

    }
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    @Override
    public void guardarTransaccion2(ConsultaExamenListaDto dto)throws NotFoundModelException {
        Integer idConsulta = null;
        try {
            ModelMapper modelMapper = new ModelMapper();
            Consulta consulta = modelMapper.map(dto.getConsulta(), Consulta.class);
            if (consulta.getDetalleConsulta() != null) {
                for (DetalleConsulta dc : consulta.getDetalleConsulta()) {
                    dc.setConsulta(consulta);
                }
            }
            repo.guardar(consulta);            
            idConsulta = repo.obtenerUltimoIdPorMedico(consulta.getMedico().getNombreMedico());            
            for (ExamenDto examen : dto.getListaExamen()) {
                ConsultaExamenDto ceDto = new ConsultaExamenDto();
                ceDto.setIdConsulta(idConsulta);
                ceDto.setIdExamen(examen.getIdExamen());
                ceDto.setInfoAdicional(examen.getInfoAdicional());
                service.guardar(ceDto);
            }
        } catch(Exception ex) {
            context.setRollbackOnly();
            throw new NotFoundModelException("Objeto no encontrado");
       }
       
    }
    @TransactionAttribute(TransactionAttributeType.NEVER)
    @Override
    public void guardarTransaccion3(ConsultaExamenListaDto dto)throws NotFoundModelException {
        Integer idConsulta = null;
        try {
            ModelMapper modelMapper = new ModelMapper();
            Consulta consulta = modelMapper.map(dto.getConsulta(), Consulta.class);
            if (consulta.getDetalleConsulta() != null) {
                for (DetalleConsulta dc : consulta.getDetalleConsulta()) {
                    dc.setConsulta(consulta);
                }
            }
            repo.guardar(consulta);            
            idConsulta = repo.obtenerUltimoIdPorMedico(consulta.getMedico().getNombreMedico());            
            for (ExamenDto examen : dto.getListaExamen()) {
                ConsultaExamenDto ceDto = new ConsultaExamenDto();
                ceDto.setIdConsulta(idConsulta);
                ceDto.setIdExamen(examen.getIdExamen());
                ceDto.setInfoAdicional(examen.getInfoAdicional());
                service.guardar(ceDto);
            }
        } catch(Exception ex) {
            context.setRollbackOnly();
            throw new NotFoundModelException("Objeto no encontrado");
       }
       
    }
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public void guardarTransaccion4(ConsultaExamenListaDto dto)throws NotFoundModelException {
        Integer idConsulta = null;
        try {
            ModelMapper modelMapper = new ModelMapper();
            Consulta consulta = modelMapper.map(dto.getConsulta(), Consulta.class);
            if (consulta.getDetalleConsulta() != null) {
                for (DetalleConsulta dc : consulta.getDetalleConsulta()) {
                    dc.setConsulta(consulta);
                }
            }
            repo.guardar(consulta);            
            idConsulta = repo.obtenerUltimoIdPorMedico(consulta.getMedico().getNombreMedico());            
            for (ExamenDto examen : dto.getListaExamen()) {
                ConsultaExamenDto ceDto = new ConsultaExamenDto();
                ceDto.setIdConsulta(idConsulta);
                ceDto.setIdExamen(examen.getIdExamen());
                ceDto.setInfoAdicional(examen.getInfoAdicional());
                service.guardar(ceDto);
            }
        } catch(Exception ex) {
            context.setRollbackOnly();
            throw new NotFoundModelException("Objeto no encontrado");
       }
       
    }
    
    

    @Override
    public void eliminar(int id) throws NotFoundModelException {
        Consulta consulta = repo.listar(id);
        if (consulta == null) {
            throw new NotFoundModelException("Objeto consulta no encontrado");
        }
        repo.eliminar(consulta);
    }

    @Override
    public void editar(ConsultaDto dto) throws NotFoundModelException {
        Consulta consulta = repo.listar(dto.getId());
        if (consulta == null) {
            throw new NotFoundModelException("Objeto consulta no encontrado");
        }

        if (dto.getMedico().getNombreMedico() != null) {
            consulta.getMedico().setNombreMedico(dto.getMedico().getNombreMedico());
        }
        if (dto.getFecha() != null) {
            consulta.setFecha(dto.getFecha());
        }

        repo.editar(consulta);
    }

    @Override
    public List<ConsultaView> listarOptimoPaginado(Integer limit, Integer offSet) {
        return repo.listarOptimoPaginado(limit, offSet);
    }
    private static String decode(String encodedString) {
    return new String(Base64.getUrlDecoder().decode(encodedString));
}

}
