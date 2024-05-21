package com.zig.hardwork.cyclomaticcomplexity;
import java.util.List;

public class ExampleThree {
    public void parse(List<Uii> responseBlock, RorInfoParticipant participant) {
        if (participant.isNewApplicant())
            resetData(participant);

        if (responseBlock == null || !participant.isNewApplicant())
            return;

        for (int i = 0; i < responseBlock.size(); i++) {
            Uii uii = responseBlock.get(i);
            switch (i) {
                case 0:
                    participant.setScores1(uii.getScore());
                    participant.setScoresCard1(uii.getScoreCard());
                    participant.setAddSmInfo1(uii.getAdditionalSmInfo());
                    participant.setReliabilityCodes1(uii.getReliabilityCodes());
                    break;
                case 1:
                    participant.setScores2(uii.getScore());
                    participant.setScoresCard2(uii.getScoreCard());
                    participant.setAddSmInfo2(uii.getAdditionalSmInfo());
                    participant.setReliabilityCodes2(uii.getReliabilityCodes());
                    break;
                case 2:
                    participant.setScores3(uii.getScore());
                    participant.setScoresCard3(uii.getScoreCard());
                    participant.setAddSmInfo3(uii.getAdditionalSmInfo());
                    participant.setReliabilityCodes3(uii.getReliabilityCodes());
                    break;
                case 3:
                    participant.setScores4(uii.getScore());
                    participant.setScoresCard4(uii.getScoreCard());
                    participant.setAddSmInfo4(uii.getAdditionalSmInfo());
                    participant.setReliabilityCodes4(uii.getReliabilityCodes());
                    break;
                case 4:
                    participant.setScores5(uii.getScore());
                    participant.setScoresCard5(uii.getScoreCard());
                    participant.setAddSmInfo5(uii.getAdditionalSmInfo());
                    participant.setReliabilityCodes5(uii.getReliabilityCodes());
                    break;
            }
        }
    }

    private void resetData(RorInfoParticipant participant) {
        //сброс значения полей
    }
}