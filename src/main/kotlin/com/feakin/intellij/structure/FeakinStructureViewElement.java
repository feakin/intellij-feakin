package com.feakin.intellij.structure;

import com.feakin.intellij.FeakinFile;
import com.feakin.intellij.psi.FeakinContextDeclaration;
import com.feakin.intellij.psi.FeakinContextMapDeclaration;
import com.feakin.intellij.psi.FeakinContextMapName;
import com.feakin.intellij.psi.FeakinContextName;
import com.feakin.intellij.psi.impl.FeakinContextMapDeclarationImpl;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FeakinStructureViewElement implements StructureViewTreeElement, SortableTreeElement  {
    private final NavigatablePsiElement myElement;

    public FeakinStructureViewElement(NavigatablePsiElement element) {
        this.myElement = element;
    }

    @Override
    public Object getValue() {
        return myElement;
    }

    @Override
    public void navigate(boolean requestFocus) {
        myElement.navigate(requestFocus);
    }

    @Override
    public boolean canNavigate() {
        return myElement.canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return myElement.canNavigateToSource();
    }

    @NotNull
    @Override
    public String getAlphaSortKey() {
        String name = myElement.getName();
        return name != null ? name : "";
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        PresentationData struct = new PresentationData();
        struct.setIcon(null);
        struct.setLocationString(null);

        if (myElement instanceof FeakinContextMapDeclarationImpl) {
            FeakinContextMapName structNameDeclaration = ((FeakinContextMapDeclarationImpl) myElement).getContextMapName();

            struct.setAttributesKey(DefaultLanguageHighlighterColors.KEYWORD);
            struct.setPresentableText(structNameDeclaration.getText());
            return struct;
        } else if (myElement instanceof FeakinContextDeclaration) {
            FeakinContextDeclaration myElement = (FeakinContextDeclaration) this.myElement;

            FeakinContextName structNameDeclaration = myElement.getContextName();

            struct.setAttributesKey(DefaultLanguageHighlighterColors.KEYWORD);
            struct.setPresentableText(structNameDeclaration.getText());
            return struct;

        }

        ItemPresentation presentation = myElement.getPresentation();
        return presentation != null ? presentation : new PresentationData();
    }

    @NotNull
    @Override
    public TreeElement[] getChildren() {
        if (myElement instanceof FeakinFile) {
            List<FeakinContextMapDeclaration> properties = PsiTreeUtil.getChildrenOfTypeAsList(myElement, FeakinContextMapDeclaration.class);
            List<TreeElement> treeElements = new ArrayList<>(properties.size());
            List<FeakinContextDeclaration> methodProperties = PsiTreeUtil.getChildrenOfTypeAsList(myElement, FeakinContextDeclaration.class);

            for (FeakinContextMapDeclaration property : properties) {
                treeElements.add(new FeakinStructureViewElement(property));
            }

            for (FeakinContextDeclaration property : methodProperties) {
                treeElements.add(new FeakinStructureViewElement(property));
            }

            return treeElements.toArray(new TreeElement[0]);
        }
        return EMPTY_ARRAY;
    }

}
