package spireCafe.interactables.merchants.timetraveler.cards;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import spireCafe.Anniv7Mod;
import spireCafe.abstracts.AbstractSCCard;
import spireCafe.interactables.merchants.timetraveler.powers.FastenPower;

@NoPools
@NoCompendium
public class Fasten extends AbstractSCCard {
    public static final String ID = Anniv7Mod.makeID(Fasten.class.getSimpleName());

    public Fasten() {
        super(ID, "TimeTravelerMerchant", 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, CardColor.COLORLESS);
        magicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FastenPower(AbstractDungeon.player, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

}
