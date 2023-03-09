package com.Debuggers.MobiliteInternational.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class ChatBotController {



        @RequestMapping(value = "/chatbot", method = RequestMethod.POST)
        @ResponseBody
        public String handleMessage(@RequestParam("message") String message) {
            String response;

            switch (message) {
                case "Bonjour":
                    response = "Bonjour, comment puis-je vous aider ?";
                    break;
                case "Comment ça va ?":
                    response = "Je vais bien, merci. Et vous ?";
                    break;
                case "Je veux passer une codedature":
                    response = "Bien sûr, quel est le produit que vous souhaitez commander ?";
                    break;
                case "Je veux ":
                    response = "Bien sûr, quel est le produit que vous souhaitez commander ?";
                    break;

                default:
                    response = "j'ai pas une solution pour votre probleme , veuillez contactez moula el moul ";
                    break;
            }

            return response;
        }

        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String index() {
            return "index";
        }

    }



