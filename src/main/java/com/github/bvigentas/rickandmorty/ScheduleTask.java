package com.github.bvigentas.rickandmorty;

import io.quarkus.scheduler.Scheduled;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class ScheduleTask {

    @Inject
    @RestClient
    RickAndMortyService rickAndMortyService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);

    @Scheduled(every = "60s")
    void rickAndMortyCharacter() {
        final Random random = new Random();
        final int characterId = random.nextInt(591);

        final Character character = rickAndMortyService.getCharacter(String.valueOf(characterId));

        LOGGER.info(String.format("O personagem %s é o personagem de número %d no Rick And Morty. Ele é da espécie %s e seu status do desenho é %s",
                character.getName(),
                character.getId(),
                character.getSpecies(),
                character.getStatus()));
    }

}
