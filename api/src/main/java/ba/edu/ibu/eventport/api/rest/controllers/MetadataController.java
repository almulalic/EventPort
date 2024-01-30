package ba.edu.ibu.eventport.api.rest.controllers;

import ba.edu.ibu.eventport.api.core.model.Category;
import ba.edu.ibu.eventport.api.core.model.Country;
import ba.edu.ibu.eventport.api.core.service.MetadataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/metadata")
public class MetadataController {
  private final MetadataService metadataService;

  public MetadataController(final MetadataService metadataService) {
    this.metadataService = metadataService;
  }

  @RequestMapping(method = RequestMethod.GET, path = "country")
  public ResponseEntity<List<Country>> getCountries() {
    return ResponseEntity.ok(metadataService.getCountries());
  }

  @RequestMapping(method = RequestMethod.GET, path = "category")
  public ResponseEntity<List<Category>> getCategories() {
    return ResponseEntity.ok(metadataService.getCategories());
  }
}
