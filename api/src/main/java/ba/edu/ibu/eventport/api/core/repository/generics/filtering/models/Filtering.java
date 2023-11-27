package ba.edu.ibu.eventport.api.core.repository.generics.filtering.models;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Filtering {
  List<Filter> filterList = new ArrayList<>();

  public void addFilter(String key, Operator operator, Object value) {
    filterList.add(new Filter(key, operator, value));
  }

  public Filtering chainFilter(String key, Operator operator, Object value) {
    filterList.add(new Filter(key, operator, value));
    return this;
  }

  public List<Filter> getFilterList() {
    return filterList;
  }

  public enum Operator {
    eq("eq"),
    gt("gt"),
    gte("gte"),
    in("in"),
    lt("lt"),
    lte("lte"),
    ne("ne"),
    nin("nin"),
    regex("regex");

    private final String operator;

    Operator(String operator) {
      this.operator = operator;
    }

    @Override
    public String toString() {
      return this.operator;
    }

    public static Operator fromString(String operator) {
      for (Operator op : Operator.values()) {
        if (op.operator.equalsIgnoreCase(operator)) {
          return op;
        }
      }
      return null;
    }
  }
}