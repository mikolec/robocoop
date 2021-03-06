package org.masteukodeu.robocoop.web;

import org.masteukodeu.robocoop.db.RoundDAO;
import org.masteukodeu.robocoop.model.Cart;
import org.masteukodeu.robocoop.model.CoopService;
import org.masteukodeu.robocoop.model.Round;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CoopController {

    private final RoundDAO roundDAO;
    private final CoopService service;

    public CoopController(RoundDAO roundDAO, CoopService service) {
        this.roundDAO = roundDAO;
        this.service = service;
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/history")
    public String history(HttpServletRequest request, Model model) {
        String username = request.getRemoteUser();
        List<Round> rounds = roundDAO.all();
        List<HistoricalCart> history = rounds.stream().map(r -> new HistoricalCart(r, service.getCartForUserAndRound(username, r.getId()))).collect(Collectors.toList());
        model.addAttribute("history", history);
        return "history";
    }

    @GetMapping("/total")
    public String total(Model model) {
        model.addAttribute("round", roundDAO.current());
        model.addAttribute("orders", service.getProductsByCategory());
        return "total";
    }

    public static class HistoricalCart {
        public final Round round;
        public final Cart cart;

        public HistoricalCart(Round round, Cart cart) {
            this.round = round;
            this.cart = cart;
        }
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
