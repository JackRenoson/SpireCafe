package spireCafe.interactables.merchants.timetraveler.cards;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import spireCafe.Anniv7Mod;
import spireCafe.abstracts.AbstractSCCard;
import spireCafe.util.Wiz;

import java.util.Iterator;

@NoPools
@NoCompendium
public class GoldAxe extends AbstractSCCard {
    public static final String ID = Anniv7Mod.makeID(GoldAxe.class.getSimpleName());

    public GoldAxe() {
        super(ID, "TimeTravelerMerchant", 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 0;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseDamage = AbstractDungeon.actionManager.cardsPlayedThisCombat.size();
        super.applyPowers();
        this.calculateCardDamage(null);
        dmg(m, AttackEffect.SLASH_HEAVY);
    }

    public void applyPowers() {
        int cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisCombat.size();
        if (cardsPlayed > 0) {
            this.baseDamage = cardsPlayed;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            this.initializeDescription();
        }

    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void upp() {
        exhaust = false;
    }

}
