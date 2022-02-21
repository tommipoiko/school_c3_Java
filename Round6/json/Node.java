import java.util.Collections;
import java.util.Iterator;

public abstract class Node {
  public boolean isValue() {
    return this instanceof ValueNode;
  }

  public boolean isArray() {
    return this instanceof ArrayNode;
  }

  public boolean isObject() {
    return this instanceof ObjectNode;
  }
  
  public void printSimple() {
    StringBuilder sb = new StringBuilder();
    printSimple(this, sb);
    System.out.print(sb.toString());
  }

  public void printJson() {
    StringBuilder sb = new StringBuilder();
    printJson(this, sb, 1, null);
    System.out.print(sb.toString());
  }
  
  private void printJson(Node node, StringBuilder sb, int indentation, Iterator parentIter) {
    char quotes = '"';
    if (node.isObject()) {
      ObjectNode objNode = (ObjectNode) node;
      sb.append("{");
      Iterator<String> iter = objNode.iterator();
      boolean hasItems = false;
      if (iter.hasNext()) {
        sb.append(NL);
        hasItems = true;
      }
      while (iter.hasNext()) {
        String name = iter.next();
        sb.append(repeatIndentation(indentation));
        sb.append(quotes + name + quotes).append(": ");
        printJson(objNode.get(name), sb, indentation + 1, iter);
      }
      if (hasItems) {
        sb.append(repeatIndentation(indentation - 1));
      }
      if (parentIter != null && parentIter.hasNext()) {
        sb.append("},").append(NL);
      } else {
        sb.append("}").append(NL);
      }
    } else if (node.isArray()) {
      sb.append("[");
      ArrayNode arrNode = (ArrayNode) node;
      Iterator<Node> iter = arrNode.iterator();
      boolean hasItems = false;
      if (iter.hasNext()) {
        sb.append(NL);
        hasItems = true;
      }
      while (iter.hasNext()) {
        Node aNode = iter.next();
        sb.append(repeatIndentation(indentation));
        printJson(aNode, sb, indentation + 1, iter);
      }
      if (hasItems) {
        sb.append(repeatIndentation(indentation - 1));
      }
      if (parentIter != null && parentIter.hasNext()) {
        sb.append("],").append(NL);
      } else {
        sb.append("]").append(NL);
      }
    } else if (node.isValue()) {
      ValueNode valNode = (ValueNode) node;
      String valStr = "null";
      if (valNode.isNumber()) {
        valStr = numberToString(valNode.getNumber());
      } else if (valNode.isBoolean()) {
        valStr = Boolean.toString(valNode.getBoolean());
      } else if (valNode.isString()) {
        valStr = "\"" + valNode.getString() + "\"";
      }
      sb.append(String.format("%s", valStr));
      if (parentIter != null && parentIter.hasNext()) {
        sb.append(",");
      }
      sb.append(NL);
    }

  }

  private String repeatIndentation(int n) {
    return String.join("", Collections.nCopies(n, "  "));
  }

  private static final String NL = System.lineSeparator();

  private static String numberToString(Double d) {
    String str = Double.toString(d);
    if(str.endsWith(".0")) {
      str = str.substring(0, str.length() - 2);
    }
    return str;
  }

  private void printSimple(Node node, StringBuilder sb) {
    if(node.isObject()) {
      sb.append("ObjectNode").append(NL);
      ObjectNode objNode = (ObjectNode) node;
      for(String name : objNode) {
        sb.append(name).append(": ");
        printSimple(objNode.get(name), sb);
      }
    }
    else if(node.isArray()) {
      sb.append("ArrayNode").append(NL);
      ArrayNode arrNode = (ArrayNode) node;
      for(Node aNode : arrNode) {
        printSimple(aNode, sb);
      }
    }
    else if(node.isValue()) {
      ValueNode valNode = (ValueNode) node;
      String typeStr = "NullValue";
      String valStr = "null";
      if(valNode.isNumber()) {
        typeStr = "NumberValue";
        valStr = numberToString(valNode.getNumber());
      }
      else if(valNode.isBoolean()) {
        typeStr = "BooleanValue";
        valStr = Boolean.toString(valNode.getBoolean());
      }
      else if(valNode.isString()) {
        typeStr = "StringValue";
        valStr = "\"" + valNode.getString() + "\"";
      }
      sb.append(String.format("%s(%s)%n", typeStr, valStr));
    }
  }
}
