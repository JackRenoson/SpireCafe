package spireCafe.interactables.merchants.timetraveler.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import spireCafe.abstracts.AbstractSCPower;
import spireCafe.interactables.merchants.timetraveler.TimeTravelerMerchant;
import spireCafe.interactables.patrons.trashking.TrashKingPatron;

import static spireCafe.Anniv7Mod.makeID;

public class FastenPower extends AbstractSCPower {
    public static String POWER_ID = makeID(FastenPower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public FastenPower(AbstractCreature owner, int amount) {
        super(POWER_ID, strings.NAME, "TimeTravelerMerchant", PowerType.BUFF, false, owner, amount);
    }

    public float modifyBlock(float blockAmount, AbstractCard card) {
        if (card.hasTag(AbstractCard.CardTags.STARTER_DEFEND)) {
            return blockAmount + this.amount;
        } return blockAmount;
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
