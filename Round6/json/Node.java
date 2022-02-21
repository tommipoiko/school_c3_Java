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
    sb.append("{").append(NL);
    printJson(this, sb, 0);
    sb.append("}").append(NL);
    System.out.print(sb.toString());
  }
  
  private void printJson(Node node, StringBuilder sb, int level) {
    if(node.isObject()) {
      ObjectNode objNode = (ObjectNode) node;
      for(String name : objNode) {
        for (int i = 0; i <= 2*level; i++) {
          sb.append(" ");
        }
        sb.append("\"").append(name).append("\"").append(": ");
        if (objNode.get(name).isObject()) {
          sb.append("{\n");
        } else if (objNode.get(name).isArray()) {
          sb.append("[\n");
        }
        printJson(objNode.get(name), sb, level+1);
        if (objNode.get(name).isObject()) {
          for (int i = 0; i <= 2*level; i++) {
            sb.append(" ");
          }
          sb.append("},\n");
        } else if (objNode.get(name).isArray()) {
          for (int i = 0; i <= 2*level; i++) {
            sb.append(" ");
          }
          sb.append("],\n");
        }
      }
    }
    else if(node.isArray()) {
      ArrayNode arrNode = (ArrayNode) node;
      for(Node aNode : arrNode) {
        for (int i = 0; i <= 2*level; i++) {
          sb.append(" ");
        }
        sb.append("{\n");
        printJson(aNode, sb, level+1);
        for (int i = 0; i <= 2*level; i++) {
          sb.append(" ");
        }
        sb.append("},\n");
      }
    }
    else if(node.isValue()) {
      ValueNode valNode = (ValueNode) node;
      String valStr = "null";
      if(valNode.isNumber()) {
        valStr = numberToString(valNode.getNumber());
      }
      else if(valNode.isBoolean()) {
        valStr = Boolean.toString(valNode.getBoolean());
      }
      else if(valNode.isString()) {
        valStr = "\"" + valNode.getString() + "\"";
      }
      sb.append(String.format("%s%n", valStr));
    }
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
