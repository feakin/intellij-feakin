package com.feakin.intellij.parser;

import com.feakin.intellij.FeakinIcons;
import com.feakin.intellij.lexer.FeakinTypes;
import com.feakin.intellij.psi.FeakinContextMapDeclaration;
import com.feakin.intellij.psi.FeakinNameComponent;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class FeakinPsiImplUtil {
    static void qualifiedName(PsiBuilder builder, int level, GeneratedParserUtilBase.Parser parser) {

    }

    public static PsiReference getReference(final FeakinNameComponent namePart) {
        return null;
    }

    public static String getKey(FeakinContextMapDeclaration element) {
        ASTNode keyNode = element.getNode().findChildByType(FeakinTypes.CONTEXT_MAP_DECLARATION);
        if (keyNode != null) {
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getName(FeakinContextMapDeclaration element) {
        return getKey(element);
    }


    public static ItemPresentation getPresentation(final FeakinContextMapDeclaration element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getName();
            }

            @Nullable
            @Override
            public String getLocationString() {
                PsiFile containingFile = element.getContainingFile();
                return containingFile == null ? null : containingFile.getName();
            }

            @Override
            public Icon getIcon(boolean unused) {
                return FeakinIcons.FILE;
            }
        };
    }
}
