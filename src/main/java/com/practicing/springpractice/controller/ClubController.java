package com.practicing.springpractice.controller;

import com.practicing.springpractice.dto.ClubDto;
import com.practicing.springpractice.model.Club;
import com.practicing.springpractice.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clubs")
public class ClubController {
    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/list")
    public String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @PostMapping("/new")
    public String saveClub(@ModelAttribute("club") Club club) {
        clubService.saveClub(club);

        return "redirect:/clubs/list";
    }

    @GetMapping("/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model) {
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @PostMapping("/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @ModelAttribute("club") ClubDto club) {
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs/list";
    }

}
