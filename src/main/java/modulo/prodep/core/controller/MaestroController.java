package modulo.prodep.core.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import modulo.prodep.core.configuration.DocumentState;
import modulo.prodep.core.configuration.Pages;
import modulo.prodep.core.model.Comprobacion;
import modulo.prodep.core.model.Fc;
import modulo.prodep.core.model.Rubro;
import modulo.prodep.core.model.Usuario;
import modulo.prodep.core.repository.BaseRep;
import modulo.prodep.core.repository.ComprobacionRepository;
import modulo.prodep.core.repository.FcRepository;

@Controller
@RequestMapping("/")
public class MaestroController {
    
    @Autowired
    private BaseRep<Rubro> repositorio;

    @Autowired
    private FcRepository repositorioFc;

    @Autowired
    private ComprobacionRepository comprobacionRepository;

    //Se pueden poner en application.properties
    public final Path uploadsRootFolder = Paths.get("src/main/resources/static");
    public final Path uploadsFolder = Paths.get("uploads");

    //Usuario de prueba, ya que ese modulo es aparte (?)
    private Usuario maestro = new Usuario(2, "Arturo Jeremías", "Cañedo", "Nuñez", 2);

    //Pagina que muestra todos los rubros
    @GetMapping(path="/")
    public ModelAndView Inicio(Pageable pageable){
        ModelAndView modelAndView =  new ModelAndView(Pages.HOME);
        List<Rubro> rubros = repositorio.readAll(pageable);
        modelAndView.addObject("rubros", rubros);
        return modelAndView;
    }

    //Muestra las formas de comprobar un rubro
    @GetMapping(path="/rubro/{id}")
    public ModelAndView Rubros(@PathVariable(required = true, name = "id") int id) throws IOException{
        ModelAndView modelAndView = new ModelAndView(Pages.RUBRO);
        List<Fc> fcs = repositorioFc.readByRubro(id);
        if(!fcs.isEmpty()){
            String nombre = fcs.get(0).getRubro().getNombre();
            modelAndView.addObject("nombreRubro", nombre);
            modelAndView.addObject("fcs", fcs);
        }else{
            modelAndView.setViewName(Pages.ERROR);
        }
        return modelAndView;
    }

    //Muestra los documentos de una fc
    @GetMapping(path="/comprobar/{id}")
    public ModelAndView Fcs(
        @PathVariable(required = true, name = "id") int id,
        @RequestParam(name = "subida", required = false) String respuestaSubida,
        @RequestParam(name = "delete", required = false) String respuestaDelete,
        @RequestParam(name = "mensajeError", required = false) String respuestaDeleteMensaje
        ){
        ModelAndView modelAndView = new ModelAndView(Pages.FC);

        //se obtiene la forma de comprobar por el id de la url, si no se encuentra una se manda a la página de error.
        Fc fc = repositorioFc.readById(id);
        if(fc == null){
            modelAndView.setViewName(Pages.ERROR);
            return modelAndView;
        }

        //Se busca por una comprobación para el usuario y la forma de comprobar actuales.
        //Si hay una manda la comporbacion para mostrarla.
        List<Comprobacion> comprobacion = comprobacionRepository.readByUserAndFc(this.maestro.getId_usuario(), id);
        modelAndView.addObject("nombre", fc.getNombre());
        modelAndView.addObject("info", fc.getDescripcion());
        modelAndView.addObject("id", fc.getId_fc());
        if(comprobacion.size() > 0){
            modelAndView.addObject("comprobacion", comprobacion.get(0));
        }else{
            modelAndView.addObject("comprobacion", null);
        }
        //System.out.println(comprobacion);

        return modelAndView;
    }

    //Aquí hay que modificar si se va a guardar en directorio(de ser así si se renombrará) o en la BD.
    //Si se renombra -> {id_usuario}_{nombre_rubro}_fc{id_fc}
    @PostMapping(path={"/comprobar/upload"})
	public String uploadFile(
        @RequestParam("file") MultipartFile file,
        @RequestParam("id_fc") String _id_fc,
        RedirectAttributes attr
        ) throws IOException {
        String pagina = "redirect:/comprobar/";

        if(_id_fc == null){
            attr.addAttribute("subida", "ERROR");
            attr.addAttribute("mensajeError", "Id invalida");
            //return new ResponseEntity<>("Id invalida", HttpStatus.BAD_REQUEST);
            pagina = "redirect:/";
            return pagina;
        }

        pagina += _id_fc;

		if (file == null || file.isEmpty()) {
            //return new ResponseEntity<>("Por favor seleccione un archivo", HttpStatus.BAD_REQUEST);
            attr.addAttribute("subida", "ERROR");
            attr.addAttribute("mensajeError", "Por favor seleccione un archivo.");
            return pagina;
        }
        
        int id_Fc = Integer.parseInt(_id_fc);
		Files.copy(file.getInputStream(), this.uploadsRootFolder.resolve(this.uploadsFolder.resolve(file.getOriginalFilename())));
        
        //hacer una entrada a la base de datos
        Comprobacion comprobacion = new Comprobacion(
            -1, this.maestro.getId_usuario(), id_Fc,
            "\\" + this.uploadsFolder.resolve(file.getOriginalFilename()).toString(), new Date(), DocumentState.EN_REVISION, new Date(), null);
        int generated_id = comprobacionRepository.create(comprobacion);
        if(generated_id != -1){
            //return new ResponseEntity<>("Archivo cargado correctamente ["+this.uploadsRootFolder.resolve(this.uploadsFolder.resolve(file.getOriginalFilename()))+"]", HttpStatus.OK);
            attr.addAttribute("subida", "OK");
        }else{
            //return new ResponseEntity<>(".", HttpStatus.INTERNAL_SERVER_ERROR);
            attr.addAttribute("subida", "ERROR");
            attr.addAttribute("mensajeError", "Error al registrar el archivo en la BD.");
            
        }
        return pagina;
    }
    
    //Página que elimina un registro de combrobacion y le documento, redirige a /comprobar/{id}
    @PostMapping(path={"/comprobar/delete"})
    public String deleteFile(
        @RequestParam(required = true, name = "id_comp") int id_comp,
        @RequestParam(required = true, name = "url") String url,
        @RequestParam(required = true, name = "id_fc") int id_fc,
        RedirectAttributes attr
    ){
        Path urlArchivo = Paths.get(this.uploadsRootFolder.toString(), url);
        File archivo = new File(urlArchivo.toString());
        String pagina = "redirect:/comprobar/" + id_fc;

        if (!archivo.exists()) {
            attr.addAttribute("delete", "ERROR");
            attr.addAttribute("mensajeError", "El archivo no existe.");
            System.out.println("El archivo data no existe.");
            return pagina;
        }

        if (!archivo.delete()){
            attr.addAttribute("delete", "ERROR");
            attr.addAttribute("mensajeError", "El archivo no se pudo eliminar.");
            System.out.println("El archivo no se pudo eliminar.");
            return pagina;
        }

        boolean res = comprobacionRepository.deleteById(id_comp);
        if(!res){
            attr.addAttribute("delete", "ERROR");
            attr.addAttribute("mensajeError", "Error al eliminar el registro");
            return pagina;
        }

        attr.addAttribute("delete", "OK");
        System.out.println(urlArchivo.toString());
        return pagina;
    }

    //Página que muestra los documentos subidos de un maestro
    @GetMapping(path={"/overview"})
    public ModelAndView overview(){
        ModelAndView modelAndView = new ModelAndView(Pages.OVERVIEW);
        List<Comprobacion> comprobaciones = comprobacionRepository.readByUser(this.maestro.getId_usuario());
        List<Comprobacion> comprobacionesAceptadas = new ArrayList<Comprobacion>();
        List<Comprobacion> comprobacionesPendientes = new ArrayList<Comprobacion>();
        //separar en pendientes (revision y rechazadas) y aprobadas
        comprobaciones.forEach(comprobacion -> {
            if(comprobacion.getEstado() == DocumentState.ACEPTADO){
                comprobacionesAceptadas.add(comprobacion);
            }else{
                comprobacionesPendientes.add(comprobacion);
            }
        });

        modelAndView.addObject("comprobacionesAceptadas", comprobacionesAceptadas);
        modelAndView.addObject("comprobacionesPendientes", comprobacionesPendientes);

        return modelAndView;
    }
}
