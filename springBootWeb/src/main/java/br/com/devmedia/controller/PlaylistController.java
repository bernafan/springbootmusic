package br.com.devmedia.controller;

import br.com.devmedia.domain.Playlist;
import br.com.devmedia.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("playlist")    // recebe requisao em localhost:8080/playlists
public class PlaylistController {

    @Autowired  // Spring faz injecao dessa dependencia quando o controller for criado
    private PlaylistService playlistService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {    // Representa um map feito pelo spring para enviar dados para view
        model.addAttribute("playlists", playlistService.recuperar());
        return new ModelAndView("/playlists/list", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("playlist") Playlist playlist) {   // modelAtribute faz o binding do
                                                                                // objeto playlist manipulado no
                                                                                // formulário de cadastro e
                                                                                // o objeto playlist esperado pelo controller
        return "/playlists/add";
    }



    /*
    * A anotacao modelAttibute est￿á recebendo o objeto playlist com nome e descricao
    * @valid est￿á validando estes atributos, para isso necessitamos do parametro binding result que trará respectivos erros de validacao
    *
    * */
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("playlist")
                                 Playlist playlist, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/playlists/add";
        }

        playlistService.salvar(playlist);
        attr.addFlashAttribute("mensagem", "Playlist criada com sucesso.");
        return "redirect:/playlist/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable ("id") long id, ModelMap model ) {
        Playlist playlist = playlistService.recuperarPorId(id);
        model.addAttribute("playlist", playlist);
        return new ModelAndView("/playlists/add", model);

    }

    @PutMapping("/salvar") // put is used to update
    public String atualizar(@Valid @ModelAttribute("playlist") Playlist playlist, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/playlists/add";
        }
        playlistService.atualizar(playlist);
        attr.addFlashAttribute("mensagem", "Playlist atualizada com sucesso!");
        return "redirect:/playlist/listar";
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") Long id, RedirectAttributes attr) {
        playlistService.excluir(id);
        attr.addFlashAttribute("mensagem", "Playlist excluída com sucesso.");
        return  "redirect:/playlist/listar";

    }
    /*
    * De forma simples, o redirect leva o browser a enviar uma nova requisição para o servidor,
    * o que faz com que a URL acessada passe a representar esse novo endereço. Caso fizéssemos um forward
    * , a URL exibida no browser continuaria a mesma. Assim, se o usuário fizer um refresh, isto é,
    * atualizar a página, a mesma requisição seria executada, levando consigo os mesmos dados da playlist.
    * O resultado disso é que o usuário, mesmo sem querer, criaria uma playlist repetida no banco.
    * Por isso é comum após a criação, atualização e exclusão de algum registro fazer um redirect ao invés de um forward.
    *
    *  */
}

