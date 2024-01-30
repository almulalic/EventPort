package ba.edu.ibu.eventport.api.core.service;

import ba.edu.ibu.eventport.api.core.model.Category;
import ba.edu.ibu.eventport.api.core.model.Country;
import ba.edu.ibu.eventport.api.core.repository.CategoryRepository;
import ba.edu.ibu.eventport.api.core.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetadataService {

  private final CountryRepository countryRepository;
  private final CategoryRepository categoryRepository;

  public List<Country> getCountries() {
    return countryRepository.findAll();
  }

  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }
}
