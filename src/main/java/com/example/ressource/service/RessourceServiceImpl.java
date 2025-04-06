package com.example.ressource.service;

import com.example.ressource.entity.Ressource;
import com.example.ressource.repository.RessourceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class RessourceServiceImpl implements IRessourceService {
    RessourceRepository ressourceRepository;
    SummaryService summary;
    private final Path rootLocation = Paths.get("upload-dir");

    @Override
    public List<Ressource> retrieveAllRessources() {
        return ressourceRepository.findAll();
           }

    @Override
    public Ressource retrieveRessource(Long rId) {
        return ressourceRepository.findById(rId).get();
            }

    @Override
    public Ressource addRessource(Ressource ressource, MultipartFile pdfFile) {
        if (pdfFile != null && !pdfFile.isEmpty()) {
            try {
                // Créer le répertoire s'il n'existe pas
                if (!Files.exists(rootLocation)) {
                    Files.createDirectories(rootLocation);
                }

                // Générer un nom de fichier unique
                String filename = UUID.randomUUID() + "-" + pdfFile.getOriginalFilename();
                Files.copy(pdfFile.getInputStream(), this.rootLocation.resolve(filename));
                ressource.setPdf(filename);
            } catch (IOException e) {
                throw new RuntimeException("Erreur lors de l'enregistrement du fichier", e);
            }
        }
        return ressourceRepository.save(ressource);
    }


    @Override
    public void removeRessource(Long rId) {
        ressourceRepository.deleteById(rId);

    }

    @Override
    public Ressource modifyRessource(Ressource r) {
        return ressourceRepository.save(r);
    }

    @Override
    public Map<String, Long> getNombreRessourcesParType() {
        List<Object[]> result = ressourceRepository.countRessourcesByType();
        Map<String, Long> stats = new HashMap<>();
        for (Object[] row : result) {
            stats.put(row[0].toString(), (Long) row[1]);
        }
        return stats;
    }
    @Override
    public String generateSummaryForRessource(Long id) throws IOException {
        Ressource ressource = ressourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ressource introuvable"));

        return summary.generateSummary(ressource);
    }
}
