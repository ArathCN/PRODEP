package modulo.prodep.core.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import modulo.prodep.core.configuration.DocumentState;
import modulo.prodep.core.model.Comprobacion;
import modulo.prodep.core.model.Fc;
import modulo.prodep.core.model.Rubro;
import modulo.prodep.core.repository.ComprobacionRepository;
import modulo.prodep.core.repository.FcRepository;
import modulo.prodep.core.repository.RubroRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FcRepository fcRepository;

    @Autowired
    private RubroRepository rubroRepository;

    @Autowired
    private ComprobacionRepository comprobacionRepository;

    //HttpServletRequest request;
    
    @GetMapping
    public ModelAndView inicio(
        @RequestParam(defaultValue = "all", required = false) String view_name,
        @RequestParam(name = "update", required = false) String respuestaUpdate,
        Pageable pageable
    ){
        //System.out.println("res: " + respuestaUpdate);
        ModelAndView modelAndView = new ModelAndView("admin/index");
        List<Comprobacion> comprobaciones = comprobacionRepository.readAllCompleteInfo(DocumentState.EN_REVISION, pageable);
        List<Comprobacion> comprobacionesAceptadas = comprobacionRepository.readAllCompleteInfo(DocumentState.ACEPTADO, pageable);
        modelAndView.addObject("comprobaciones", comprobaciones);
        modelAndView.addObject("comprobacionesAceptadas", comprobacionesAceptadas);
        return modelAndView;
    }

    @PostMapping
    public String inicioUpdate(
        @RequestParam(defaultValue = "-1", required = true) int id,
        @RequestParam(defaultValue = "none", required = true) String accion,
        @RequestParam(required = false) String comentario,
        RedirectAttributes attr
    ){
        if(id != -1 && (accion.equals("aceptar") || (accion.equals("rechazar") && comentario.length() <= 250) ) ){
            boolean res = false;
            Comprobacion comprobacion = new Comprobacion();
            comprobacion.setId_comp(id);
            comprobacion.setUltima_revision(new Date()); //Comprobacion.df.format(new Date())
            if(accion.equals("aceptar")){
                comprobacion.setEstado(DocumentState.ACEPTADO);
                res = comprobacionRepository.updateAceptar(comprobacion);
            }else if(accion.equals("rechazar") && comentario != null){
                
                comprobacion.setEstado(DocumentState.RECHAZADO);
                comprobacion.setComentario(comentario);
                res = comprobacionRepository.updateRechazar(comprobacion);
            }

            if(res) attr.addAttribute("update", "OK"); else attr.addAttribute("update", "ERROR");
            
        }else attr.addAttribute("update", "ERROR");
        return "redirect:/admin";
    }

    @GetMapping(path="/fc")
    public ModelAndView fc(){
        int limite = 60;
        ModelAndView modelAndView = new ModelAndView("admin/fc");
        List<Fc> fcs = fcRepository.readAll(new Pageable());
        fcs.forEach(fc ->{
            String des = fc.getDescripcion();
            int maxLength = (des.length() < limite)?des.length():limite;
            des = des.substring(0, maxLength) + "...";
            fc.setDescripcion(des);
        });
        modelAndView.addObject("fcs", fcs);
        return modelAndView;
    }

    @GetMapping(path="/rubro")
    public ModelAndView rubro(){
        int limite = 60;
        ModelAndView modelAndView = new ModelAndView("admin/rubro");
        List<Rubro> rubros = rubroRepository.readAll(new Pageable());
        rubros.forEach(rubro ->{
            String des = rubro.getDescripcion();
            int maxLength = (des.length() < limite)?des.length():limite;
            des = des.substring(0, maxLength) + "...";
            rubro.setDescripcion(des);
        });
        modelAndView.addObject("rubros", rubros);
        return modelAndView;
    }
}
