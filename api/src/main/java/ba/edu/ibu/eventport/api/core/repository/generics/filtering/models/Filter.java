package ba.edu.ibu.eventport.api.core.repository.generics.filtering.models;

public class Filter {
  private final String key;
  private final Filtering.Operator operator;
  private final Object value;

  public Filter(String key, Filtering.Operator operator, Object value) {
    this.key = key;
    this.operator = operator;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public Filtering.Operator getOperator() {
    return operator;
  }

  public Object getValue() {
    return value;
  }

  @Override
  public String toString() {
    return getKey() + getOperator() + getValue();
  }
}