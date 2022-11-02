package com.feakin.intellij.highlight

import com.feakin.intellij.FkIcons
import com.feakin.intellij.FkLanguage
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.options.colors.RainbowColorSettingsPage

class FkColorSettingsPage : ColorSettingsPage, RainbowColorSettingsPage {
    override fun getLanguage() = FkLanguage
    override fun getIcon() = FkIcons.FILE
    override fun getHighlighter(): SyntaxHighlighter = FkSyntaxHighlighter()

    override fun getAttributeDescriptors(): Array<AttributesDescriptor>  = emptyArray()

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String {
        return FkLanguage.displayName
    }


    override fun getDemoText(): String {
        return """ContextMap TicketBooking {
  ReservationCtx <-> UserCtx;
}

Context ReservationCtx {}

Context UserCtx {
  Aggregate Ticket;
}

Aggregate Ticket {
  Entity Ticket;
}

Entity Ticket {
  Struct {
    id: UUID;
    seat: String;
    price: Int;
  }
}


layered DDD {
  dependency {
    interface -> application
    application -> domain
    interface -> domain
    application -> infrastructure
    interface -> infrastructure
  }
  layer interface {
    package: "com.example.book";
  }
  layer domain {
    package: "com.example.domain";
  }
  layer application {
    package: "com.example.application";
  }
  layer infrastructure {
    package: "com.example.infrastructure";
  }
}


impl PackageJsonGet {
  endpoint {
    GET "https://raw.githubusercontent.com/feakin/vscode-feakin/master/package.json";
  }
}

// binding;
impl UserCreated {
  endpoint {
    POST "/user/{id}";
    authorization: Basic admin admin;
    response: User;
  }

  flow {
    via UserRepository::getUserById receive user: User
    via UserRepository::save(user: User) receive user: User;
    via Kafak send User to "user.create";
  }
}

env Local {
  datasource {
    driver: postgresql
    host: "localhost"
    port: 5432
    database: "test"
  }

  server {
    port: 9090;
  }

  kafka {
    host: "localhost"
    port: 9092
  }
}

env Staging {
  datasource {
    url: "mysql://localhost:3306/test"
  }
}
"""
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? {
        return mutableMapOf()
    }

    override fun isRainbowType(type: TextAttributesKey?): Boolean {
        return false
    }
}