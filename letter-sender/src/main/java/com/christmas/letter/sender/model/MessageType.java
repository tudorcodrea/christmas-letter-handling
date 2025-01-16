package com.christmas.letter.sender.model;

public enum MessageType {
  LETTER("Letter"), TELEGRAM("Telegram"), OTHER("Other");


  private String messageType;

  private MessageType(String category) {
    this.messageType = category;
  }

  public static MessageType fromString(String messageType) {
    for (MessageType wishCategory : MessageType.values()) {
      if (wishCategory.messageType.equalsIgnoreCase(messageType)) {
        return wishCategory;
      }
    }
    return OTHER;
  }

  public String getMessageType() {
    return messageType;
  }
}
