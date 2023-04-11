package com.koussay.jeux.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.koussay.jeux.entities.Jeu;


public interface JeuRepository extends JpaRepository<Jeu, Long> {

}
