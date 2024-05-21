package com.zig.hardwork.cyclomaticcomplexity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExampleThreeProceed {
    public void parse(List<Uii> responseBlock, RorInfoParticipant participant) {
        if (!participant.isNewApplicant())
            return;

        resetData(participant);

        Optional.ofNullable(responseBlock).ifPresent(uiis -> {
            List<BiConsumer<RorInfoParticipant, Uii>> filledConsumers = Stream.of(
                    setUiiOneValues,
                    setUiiTwoValues,
                    setUiiThreeValues,
                    setUiiForthValues,
                    setUiiFiveValues).toList();

            Map<Integer, BiConsumer<RorInfoParticipant, Uii>> filledConsumerByIdx = IntStream.range(0, filledConsumers.size())
                    .boxed()
                    .collect(Collectors.toMap(Function.identity(), filledConsumers::get));

            IntStream.range(0, uiis.size())
                    .boxed()
                    .forEach(i -> filledConsumerByIdx.get(i).accept(participant, uiis.get(i)));
        });
    }

    private void resetData(RorInfoParticipant participant) {
        //сброс значения полей
    }

    private final BiConsumer<RorInfoParticipant, Uii> setUiiOneValues = ((participant, uii) -> {
        participant.setScores1(uii.getScore());
        participant.setScoresCard1(uii.getScoreCard());
        participant.setAddSmInfo1(uii.getAdditionalSmInfo());
        participant.setReliabilityCodes1(uii.getReliabilityCodes());
    });

    private final BiConsumer<RorInfoParticipant, Uii> setUiiTwoValues = ((participant, uii) -> {
        participant.setScores2(uii.getScore());
        participant.setScoresCard2(uii.getScoreCard());
        participant.setAddSmInfo2(uii.getAdditionalSmInfo());
        participant.setReliabilityCodes2(uii.getReliabilityCodes());
    });

    private final BiConsumer<RorInfoParticipant, Uii> setUiiThreeValues = ((participant, uii) -> {
        participant.setScores3(uii.getScore());
        participant.setScoresCard3(uii.getScoreCard());
        participant.setAddSmInfo3(uii.getAdditionalSmInfo());
        participant.setReliabilityCodes3(uii.getReliabilityCodes());
    });

    private final BiConsumer<RorInfoParticipant, Uii> setUiiForthValues = ((participant, uii) -> {
        participant.setScores4(uii.getScore());
        participant.setScoresCard4(uii.getScoreCard());
        participant.setAddSmInfo4(uii.getAdditionalSmInfo());
        participant.setReliabilityCodes4(uii.getReliabilityCodes());
    });

    private final BiConsumer<RorInfoParticipant, Uii> setUiiFiveValues = ((participant, uii) -> {
        participant.setScores5(uii.getScore());
        participant.setScoresCard5(uii.getScoreCard());
        participant.setAddSmInfo5(uii.getAdditionalSmInfo());
        participant.setReliabilityCodes5(uii.getReliabilityCodes());
    });
}


@Getter @Setter
class Uii {
    private BigDecimal score;
    private String scoreCard;
    private String additionalSmInfo;
    private String reliabilityCodes;
}

interface RorInfoParticipant extends RorInfoUiis{
    boolean isNewApplicant();
}

interface RorInfoUiis {
    void setScores1(BigDecimal scores1);
    void setScores2(BigDecimal scores2);
    void setScores3(BigDecimal scores3);
    void setScores4(BigDecimal scores4);
    void setScores5(BigDecimal scores5);
    void setScoresCard1(String scoresCard1);
    void setScoresCard2(String scoresCard2);
    void setScoresCard3(String scoresCard3);
    void setScoresCard4(String scoresCard4);
    void setScoresCard5(String scoresCard5);
    void setAddSmInfo1(String addSmInfo1);
    void setAddSmInfo2(String addSmInfo2);
    void setAddSmInfo3(String addSmInfo3);
    void setAddSmInfo4(String addSmInfo4);
    void setAddSmInfo5(String addSmInfo5);
    void setReliabilityCodes1(String reliabilityCodes1);
    void setReliabilityCodes2(String reliabilityCodes2);
    void setReliabilityCodes3(String reliabilityCodes3);
    void setReliabilityCodes4(String reliabilityCodes4);
    void setReliabilityCodes5(String reliabilityCodes5);

}