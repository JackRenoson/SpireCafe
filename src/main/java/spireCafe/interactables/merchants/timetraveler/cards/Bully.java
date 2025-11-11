package spireCafe.interactables.merchants.timetraveler.cards;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import spireCafe.Anniv7Mod;
import spireCafe.abstracts.AbstractSCCard;

import java.util.Objects;

@NoPools
@NoCompendium
public class Bully extends AbstractSCCard {
    public static final String ID = Anniv7Mod.makeID(Bully.class.getSimpleName());

    public Bully() {
        super(ID, "TimeTravelerMerchant", 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardColor.RED);
        baseDamage = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int vuln = 0;
        for (AbstractPower power : m.powers) {
            if (Objects.equals(power.ID, VulnerablePower.POWER_ID)){
                vuln = power.amount;
            }
        }
        this.baseDamage = 4 + magicNumber * vuln;
        super.applyPowers();
        this.calculateCardDamage(m);
        dmg(m, AttackEffect.BLUNT_LIGHT);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

}
