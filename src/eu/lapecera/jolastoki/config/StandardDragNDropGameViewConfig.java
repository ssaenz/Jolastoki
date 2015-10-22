package eu.lapecera.jolastoki.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import eu.lapecera.jolastoki.R;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.games.GameView;
import eu.lapecera.jolastoki.games.dragndrop.StandardDragNDropGameView;

public enum StandardDragNDropGameViewConfig implements DragNDropGameViewConfig {

	parque_1_3 (GameLevel.ONE, R.layout.layout_parque_1_3, R.string.parque_1_3_title, 60000l, -1,
			Arrays.asList(R.id.parque_1_3_bt1, R.id.parque_1_3_bt2, R.id.parque_1_3_bt3, R.id.parque_1_3_bt4, R.id.parque_1_3_bt5, R.id.parque_1_3_bt6),
			new HashMap<Integer, Integer>() { 
		private static final long serialVersionUID = 1L;
		{
			put( R.id.parque_1_3_bt1, R.id.parque_1_3_target3);
			put(R.id.parque_1_3_bt2, R.id.parque_1_3_target2);
			put(R.id.parque_1_3_bt3, R.id.parque_1_3_target1);
			put(R.id.parque_1_3_bt4, R.id.parque_1_3_target6);
			put(R.id.parque_1_3_bt5, R.id.parque_1_3_target5);
			put(R.id.parque_1_3_bt6, R.id.parque_1_3_target4);
		}
	}, null),
	charca_1_3 (GameLevel.ONE, R.layout.layout_charca_1_3, R.string.charca_x_3_title, 60000l, -1,
			Arrays.asList(R.id.charca_3_figure1, R.id.charca_3_figure2, R.id.charca_3_figure3, R.id.charca_3_figure4),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.charca_3_figure4, R.id.charca_3_target1);
			put(R.id.charca_3_figure3, R.id.charca_3_target2);
			put(R.id.charca_3_figure1, R.id.charca_3_target3);
		}

	}, null),
	charca_2_3 (GameLevel.TWO, R.layout.layout_charca_2_3, R.string.charca_x_3_title, 60000l, -1,
			Arrays.asList(R.id.charca_3_figure1, R.id.charca_3_figure2, R.id.charca_3_figure3, R.id.charca_3_figure4),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.charca_3_figure3, R.id.charca_3_target1);
			put(R.id.charca_3_figure1, R.id.charca_3_target2);
			put(R.id.charca_3_figure2, R.id.charca_3_target3);
		}

	}, null),
	charca_3_3 (GameLevel.THREE, R.layout.layout_charca_3_3, R.string.charca_x_3_title, 60000l, -1,
			Arrays.asList(R.id.charca_3_figure1, R.id.charca_3_figure2, R.id.charca_3_figure3, R.id.charca_3_figure4),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.charca_3_figure2, R.id.charca_3_target1);
			put(R.id.charca_3_figure1, R.id.charca_3_target2);
			put(R.id.charca_3_figure3, R.id.charca_3_target3);
		}

	}, null),
	charca_1_2 (GameLevel.ONE, R.layout.layout_charca_1_2, R.string.charca_x_2_title, 60000l, R.drawable.charca_2_target_selected,
			Arrays.asList(R.id.charca_2_bt1, R.id.charca_2_bt2, R.id.charca_2_bt3, R.id.charca_2_bt4, R.id.charca_2_bt5, R.id.charca_2_bt6),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.charca_2_bt2, R.id.charca_1_2_target1);
			put(R.id.charca_2_bt6, R.id.charca_1_2_target2);
			put(R.id.charca_2_bt3, R.id.charca_1_2_target3);
			put(R.id.charca_2_bt1, R.id.charca_1_2_target4);
			put(R.id.charca_2_bt4, R.id.charca_1_2_target5);
			put(R.id.charca_2_bt5, R.id.charca_1_2_target6);
		}

	}, null),
	charca_2_2 (GameLevel.TWO, R.layout.layout_charca_2_2, R.string.charca_x_2_title, 60000l, R.drawable.charca_2_target_selected,
			Arrays.asList(R.id.charca_2_bt1, R.id.charca_2_bt2, R.id.charca_2_bt3, R.id.charca_2_bt4, R.id.charca_2_bt5, R.id.charca_2_bt6),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.charca_2_bt5, R.id.charca_2_2_target1);
			put(R.id.charca_2_bt6, R.id.charca_2_2_target2);
			put(R.id.charca_2_bt4, R.id.charca_2_2_target3);
			put(R.id.charca_2_bt2, R.id.charca_2_2_target4);
			put(R.id.charca_2_bt3, R.id.charca_2_2_target5);
			put(R.id.charca_2_bt1, R.id.charca_2_2_target6);
		}

	}, null),
	charca_3_2 (GameLevel.THREE, R.layout.layout_charca_3_2, R.string.charca_x_2_title, 60000l, R.drawable.charca_2_target_selected,
			Arrays.asList(R.id.charca_2_bt1, R.id.charca_2_bt2, R.id.charca_2_bt3, R.id.charca_2_bt4, R.id.charca_2_bt5, R.id.charca_2_bt6),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.charca_2_bt2, R.id.charca_3_2_target1);
			put(R.id.charca_2_bt6, R.id.charca_3_2_target2);
			put(R.id.charca_2_bt3, R.id.charca_3_2_target3);
			put(R.id.charca_2_bt1, R.id.charca_3_2_target4);
			put(R.id.charca_2_bt4, R.id.charca_3_2_target5);
			put(R.id.charca_2_bt5, R.id.charca_3_2_target6);
		}

	}, null),
	mercado_1_3 (GameLevel.ONE, R.layout.layout_mercado_1_3, R.string.mercado_1_3_title, 60000l, -1,
			Arrays.asList(R.id.mercado_1_3_figure1, R.id.mercado_1_3_figure2, R.id.mercado_1_3_figure2, R.id.mercado_1_3_figure3, R.id.mercado_1_3_figure4, R.id.mercado_1_3_figure5, R.id.mercado_1_3_figure6),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.mercado_1_3_figure1, R.id.mercado_1_3_target1);
			put(R.id.mercado_1_3_figure2, R.id.mercado_1_3_target2);
			put(R.id.mercado_1_3_figure3, R.id.mercado_1_3_target3);
			put(R.id.mercado_1_3_figure4, R.id.mercado_1_3_target4);
			put(R.id.mercado_1_3_figure5, R.id.mercado_1_3_target5);
			put(R.id.mercado_1_3_figure6, R.id.mercado_1_3_target6);
		}

	}, null),
	mercado_2_3 (GameLevel.TWO, R.layout.layout_mercado_2_3, R.string.mercado_2_3_title, 60000l, -1,
			Arrays.asList(R.id.mercado_2_3_figure1, R.id.mercado_2_3_figure2, R.id.mercado_2_3_figure3,
							R.id.mercado_2_3_figure4, R.id.mercado_2_3_figure5, R.id.mercado_2_3_figure6,
							R.id.mercado_2_3_figure7, R.id.mercado_2_3_figure8, R.id.mercado_2_3_figure9,
							R.id.mercado_2_3_figure10, R.id.mercado_2_3_figure11, R.id.mercado_2_3_figure12,
							R.id.mercado_2_3_figure13, R.id.mercado_2_3_figure14, R.id.mercado_2_3_figure15,
							R.id.mercado_2_3_figure16, R.id.mercado_2_3_figure17, R.id.mercado_2_3_figure18),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.mercado_2_3_figure1, R.id.mercado_2_3_target1);
			put(R.id.mercado_2_3_figure2, R.id.mercado_2_3_target1);
			put(R.id.mercado_2_3_figure3, R.id.mercado_2_3_target1);
			put(R.id.mercado_2_3_figure4, R.id.mercado_2_3_target2);
			put(R.id.mercado_2_3_figure5, R.id.mercado_2_3_target2);
			put(R.id.mercado_2_3_figure6, R.id.mercado_2_3_target2);
			put(R.id.mercado_2_3_figure7, R.id.mercado_2_3_target3);
			put(R.id.mercado_2_3_figure8, R.id.mercado_2_3_target3);
			put(R.id.mercado_2_3_figure9, R.id.mercado_2_3_target3);
			put(R.id.mercado_2_3_figure10, R.id.mercado_2_3_target4);
			put(R.id.mercado_2_3_figure11, R.id.mercado_2_3_target4);
			put(R.id.mercado_2_3_figure12, R.id.mercado_2_3_target4);
			put(R.id.mercado_2_3_figure13, R.id.mercado_2_3_target5);
			put(R.id.mercado_2_3_figure14, R.id.mercado_2_3_target5);
			put(R.id.mercado_2_3_figure15, R.id.mercado_2_3_target5);
			put(R.id.mercado_2_3_figure16, R.id.mercado_2_3_target6);
			put(R.id.mercado_2_3_figure17, R.id.mercado_2_3_target6);
			put(R.id.mercado_2_3_figure18, R.id.mercado_2_3_target6);
		}

	}, new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.mercado_2_3_figure1, R.id.mercado_2_3_figure1target);
			put(R.id.mercado_2_3_figure2, R.id.mercado_2_3_figure2target);
			put(R.id.mercado_2_3_figure3, R.id.mercado_2_3_figure3target);
			put(R.id.mercado_2_3_figure4, R.id.mercado_2_3_figure4target);
			put(R.id.mercado_2_3_figure5, R.id.mercado_2_3_figure5target);
			put(R.id.mercado_2_3_figure6, R.id.mercado_2_3_figure6target);
			put(R.id.mercado_2_3_figure7, R.id.mercado_2_3_figure7target);
			put(R.id.mercado_2_3_figure8, R.id.mercado_2_3_figure8target);
			put(R.id.mercado_2_3_figure9, R.id.mercado_2_3_figure9target);
			put(R.id.mercado_2_3_figure10, R.id.mercado_2_3_figure10target);
			put(R.id.mercado_2_3_figure11, R.id.mercado_2_3_figure11target);
			put(R.id.mercado_2_3_figure12, R.id.mercado_2_3_figure12target);
			put(R.id.mercado_2_3_figure13, R.id.mercado_2_3_figure13target);
			put(R.id.mercado_2_3_figure14, R.id.mercado_2_3_figure14target);
			put(R.id.mercado_2_3_figure15, R.id.mercado_2_3_figure15target);
			put(R.id.mercado_2_3_figure16, R.id.mercado_2_3_figure16target);
			put(R.id.mercado_2_3_figure17, R.id.mercado_2_3_figure17target);
			put(R.id.mercado_2_3_figure18, R.id.mercado_2_3_figure18target);
		}

	}),
	biblioteca_1_1 (GameLevel.ONE, R.layout.layout_biblioteca_1_1, R.string.biblioteca_1_title, 60000l, -1,
			Arrays.asList(R.id.biblioteca_1_figure1, R.id.biblioteca_1_figure2, R.id.biblioteca_1_figure3,
							R.id.biblioteca_1_figure4, R.id.biblioteca_1_figure5, R.id.biblioteca_1_figure6,
							R.id.biblioteca_1_figure7, R.id.biblioteca_1_figure8, R.id.biblioteca_1_figure9,
							R.id.biblioteca_1_figure10),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.biblioteca_1_figure1, R.id.biblioteca_1_target1);
			put(R.id.biblioteca_1_figure2, R.id.biblioteca_1_target2);
			put(R.id.biblioteca_1_figure3, R.id.biblioteca_1_target3);
			put(R.id.biblioteca_1_figure4, R.id.biblioteca_1_target4);
			put(R.id.biblioteca_1_figure5, R.id.biblioteca_1_target5);
			put(R.id.biblioteca_1_figure6, R.id.biblioteca_1_target6);
			put(R.id.biblioteca_1_figure7, R.id.biblioteca_1_target7);
			put(R.id.biblioteca_1_figure8, R.id.biblioteca_1_target8);
			put(R.id.biblioteca_1_figure9, R.id.biblioteca_1_target9);
			put(R.id.biblioteca_1_figure10, R.id.biblioteca_1_target10);
		}

	}, null),
	biblioteca_2_1 (GameLevel.TWO, R.layout.layout_biblioteca_2_1, R.string.biblioteca_1_title, 60000l, -1,
			Arrays.asList(R.id.biblioteca_1_figure1, R.id.biblioteca_1_figure2, R.id.biblioteca_1_figure3,
							R.id.biblioteca_1_figure4, R.id.biblioteca_1_figure5, R.id.biblioteca_1_figure6,
							R.id.biblioteca_1_figure7, R.id.biblioteca_1_figure8, R.id.biblioteca_1_figure9,
							R.id.biblioteca_1_figure10),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.biblioteca_1_figure1, R.id.biblioteca_1_target1);
			put(R.id.biblioteca_1_figure2, R.id.biblioteca_1_target2);
			put(R.id.biblioteca_1_figure3, R.id.biblioteca_1_target3);
			put(R.id.biblioteca_1_figure4, R.id.biblioteca_1_target4);
			put(R.id.biblioteca_1_figure5, R.id.biblioteca_1_target5);
			put(R.id.biblioteca_1_figure6, R.id.biblioteca_1_target6);
			put(R.id.biblioteca_1_figure7, R.id.biblioteca_1_target7);
			put(R.id.biblioteca_1_figure8, R.id.biblioteca_1_target8);
			put(R.id.biblioteca_1_figure9, R.id.biblioteca_1_target9);
			put(R.id.biblioteca_1_figure10, R.id.biblioteca_1_target10);
		}

	}, null),
	biblioteca_3_1 (GameLevel.THREE, R.layout.layout_biblioteca_3_1, R.string.biblioteca_1_title, 60000l, -1,
			Arrays.asList(R.id.biblioteca_1_figure1, R.id.biblioteca_1_figure2, R.id.biblioteca_1_figure3,
							R.id.biblioteca_1_figure4, R.id.biblioteca_1_figure5, R.id.biblioteca_1_figure6,
							R.id.biblioteca_1_figure7, R.id.biblioteca_1_figure8, R.id.biblioteca_1_figure9,
							R.id.biblioteca_1_figure10),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.biblioteca_1_figure1, R.id.biblioteca_1_target1);
			put(R.id.biblioteca_1_figure2, R.id.biblioteca_1_target2);
			put(R.id.biblioteca_1_figure3, R.id.biblioteca_1_target3);
			put(R.id.biblioteca_1_figure4, R.id.biblioteca_1_target4);
			put(R.id.biblioteca_1_figure5, R.id.biblioteca_1_target5);
			put(R.id.biblioteca_1_figure6, R.id.biblioteca_1_target6);
			put(R.id.biblioteca_1_figure7, R.id.biblioteca_1_target7);
			put(R.id.biblioteca_1_figure8, R.id.biblioteca_1_target8);
			put(R.id.biblioteca_1_figure9, R.id.biblioteca_1_target9);
			put(R.id.biblioteca_1_figure10, R.id.biblioteca_1_target10);
		}

	}, null),
	biblioteca_1_2 (GameLevel.ONE, R.layout.layout_biblioteca_1_2, R.string.biblioteca_2_title, 60000l, R.drawable.biblioteca_2_2_pieza_base,
			Arrays.asList(R.id.biblioteca_2_figure1, R.id.biblioteca_2_figure2, R.id.biblioteca_2_figure3,
							R.id.biblioteca_2_figure4, R.id.biblioteca_2_figure5, R.id.biblioteca_2_figure6,
							R.id.biblioteca_2_figure7, R.id.biblioteca_2_figure8),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.biblioteca_2_figure1, R.id.biblioteca_2_target1);
			put(R.id.biblioteca_2_figure2, R.id.biblioteca_2_target2);
			put(R.id.biblioteca_2_figure3, R.id.biblioteca_2_target3);
			put(R.id.biblioteca_2_figure4, R.id.biblioteca_2_target4);
			put(R.id.biblioteca_2_figure5, R.id.biblioteca_2_target5);
			put(R.id.biblioteca_2_figure6, R.id.biblioteca_2_target6);
			put(R.id.biblioteca_2_figure7, R.id.biblioteca_2_target7);
			put(R.id.biblioteca_2_figure8, R.id.biblioteca_2_target8);
		}

	}, null),
	biblioteca_2_2 (GameLevel.TWO, R.layout.layout_biblioteca_2_2, R.string.biblioteca_2_title, 60000l, R.drawable.biblioteca_2_2_pieza_base,
			Arrays.asList(R.id.biblioteca_2_figure1, R.id.biblioteca_2_figure2, R.id.biblioteca_2_figure3,
							R.id.biblioteca_2_figure4, R.id.biblioteca_2_figure5, R.id.biblioteca_2_figure6,
							R.id.biblioteca_2_figure7, R.id.biblioteca_2_figure8),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.biblioteca_2_figure1, R.id.biblioteca_2_target1);
			put(R.id.biblioteca_2_figure2, R.id.biblioteca_2_target2);
			put(R.id.biblioteca_2_figure3, R.id.biblioteca_2_target3);
			put(R.id.biblioteca_2_figure4, R.id.biblioteca_2_target4);
			put(R.id.biblioteca_2_figure5, R.id.biblioteca_2_target5);
			put(R.id.biblioteca_2_figure6, R.id.biblioteca_2_target6);
			put(R.id.biblioteca_2_figure7, R.id.biblioteca_2_target7);
			put(R.id.biblioteca_2_figure8, R.id.biblioteca_2_target8);
		}

	}, null),
	biblioteca_3_2 (GameLevel.THREE, R.layout.layout_biblioteca_3_2, R.string.biblioteca_2_title, 60000l, R.drawable.biblioteca_2_2_pieza_base,
			Arrays.asList(R.id.biblioteca_2_figure1, R.id.biblioteca_2_figure2, R.id.biblioteca_2_figure3,
							R.id.biblioteca_2_figure4, R.id.biblioteca_2_figure5, R.id.biblioteca_2_figure6,
							R.id.biblioteca_2_figure7, R.id.biblioteca_2_figure8),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.biblioteca_2_figure1, R.id.biblioteca_2_target1);
			put(R.id.biblioteca_2_figure2, R.id.biblioteca_2_target2);
			put(R.id.biblioteca_2_figure3, R.id.biblioteca_2_target3);
			put(R.id.biblioteca_2_figure4, R.id.biblioteca_2_target4);
			put(R.id.biblioteca_2_figure5, R.id.biblioteca_2_target5);
			put(R.id.biblioteca_2_figure6, R.id.biblioteca_2_target6);
			put(R.id.biblioteca_2_figure7, R.id.biblioteca_2_target7);
			put(R.id.biblioteca_2_figure8, R.id.biblioteca_2_target8);
		}

	}, null),
	biblioteca_1_3 (GameLevel.ONE, R.layout.layout_biblioteca_1_3, R.string.biblioteca_1_3_title, 60000l, -1,
			Arrays.asList(R.id.biblioteca_1_3_figure1, R.id.biblioteca_1_3_figure2, R.id.biblioteca_1_3_figure3,
							R.id.biblioteca_1_3_figure4, R.id.biblioteca_1_3_figure5, R.id.biblioteca_1_3_figure6,
							R.id.biblioteca_1_3_figure7, R.id.biblioteca_1_3_figure8, R.id.biblioteca_1_3_figure9, R.id.biblioteca_1_3_figure10),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.biblioteca_1_3_figure1, R.id.biblioteca_1_3_target9);
			put(R.id.biblioteca_1_3_figure2, R.id.biblioteca_1_3_target6);
			put(R.id.biblioteca_1_3_figure3, R.id.biblioteca_1_3_target5);
			put(R.id.biblioteca_1_3_figure4, R.id.biblioteca_1_3_target10);
			put(R.id.biblioteca_1_3_figure5, R.id.biblioteca_1_3_target2);
			put(R.id.biblioteca_1_3_figure6, R.id.biblioteca_1_3_target7);
			put(R.id.biblioteca_1_3_figure7, R.id.biblioteca_1_3_target3);
			put(R.id.biblioteca_1_3_figure8, R.id.biblioteca_1_3_target8);
			put(R.id.biblioteca_1_3_figure9, R.id.biblioteca_1_3_target1);
			put(R.id.biblioteca_1_3_figure10, R.id.biblioteca_1_3_target4);
		}

	}, null),
	biblioteca_2_3 (GameLevel.TWO, R.layout.layout_biblioteca_2_3, R.string.biblioteca_2_3_title, 60000l, R.drawable.background_white_rounded,
			Arrays.asList(R.id.biblioteca_2_3_figure1, R.id.biblioteca_2_3_figure2, R.id.biblioteca_2_3_figure3,
							R.id.biblioteca_2_3_figure4, R.id.biblioteca_2_3_figure5, R.id.biblioteca_2_3_figure6,
							R.id.biblioteca_2_3_figure7, R.id.biblioteca_2_3_figure8),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.biblioteca_2_3_figure1, R.id.biblioteca_2_3_target6container);
			put(R.id.biblioteca_2_3_figure2, R.id.biblioteca_2_3_target3container);
			put(R.id.biblioteca_2_3_figure3, R.id.biblioteca_2_3_target2container);
			put(R.id.biblioteca_2_3_figure4, R.id.biblioteca_2_3_target7container);
			put(R.id.biblioteca_2_3_figure5, R.id.biblioteca_2_3_target4container);
			put(R.id.biblioteca_2_3_figure6, R.id.biblioteca_2_3_target1container);
			put(R.id.biblioteca_2_3_figure7, R.id.biblioteca_2_3_target5container);
			put(R.id.biblioteca_2_3_figure8, R.id.biblioteca_2_3_target8container);
		}

	}, new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.biblioteca_2_3_figure1, R.id.biblioteca_2_3_target6);
			put(R.id.biblioteca_2_3_figure2, R.id.biblioteca_2_3_target3);
			put(R.id.biblioteca_2_3_figure3, R.id.biblioteca_2_3_target2);
			put(R.id.biblioteca_2_3_figure4, R.id.biblioteca_2_3_target7);
			put(R.id.biblioteca_2_3_figure5, R.id.biblioteca_2_3_target4);
			put(R.id.biblioteca_2_3_figure6, R.id.biblioteca_2_3_target1);
			put(R.id.biblioteca_2_3_figure7, R.id.biblioteca_2_3_target5);
			put(R.id.biblioteca_2_3_figure8, R.id.biblioteca_2_3_target8);
		}

	}),
	biblioteca_3_3 (GameLevel.THREE, R.layout.layout_biblioteca_3_3, R.string.biblioteca_3_3_title, 60000l, -1,
			Arrays.asList(R.id.biblioteca_3_3_figure1, R.id.biblioteca_3_3_figure2, R.id.biblioteca_3_3_figure3,
							R.id.biblioteca_3_3_figure4, R.id.biblioteca_3_3_figure5, R.id.biblioteca_3_3_figure6,
							R.id.biblioteca_3_3_figure7),
			new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put(R.id.biblioteca_3_3_figure1, R.id.biblioteca_3_3_target1);
			put(R.id.biblioteca_3_3_figure2, R.id.biblioteca_3_3_target2);
			put(R.id.biblioteca_3_3_figure3, R.id.biblioteca_3_3_target3);
			put(R.id.biblioteca_3_3_figure4, R.id.biblioteca_3_3_target4);
			put(R.id.biblioteca_3_3_figure5, R.id.biblioteca_3_3_target5);
			put(R.id.biblioteca_3_3_figure6, R.id.biblioteca_3_3_target6);
			put(R.id.biblioteca_3_3_figure7, R.id.biblioteca_3_3_target7);
		}

	}, null);

	private GameLevel level;
	private int layout;
	private long time;
	private int title;
	private List<Integer> figures;
	private Map<Integer, Integer> targets;
	private int endTargetBackground;
	private Map<Integer, Integer> multiFigureTargetBackgound;

	private StandardDragNDropGameViewConfig(GameLevel level, int layout, int title, long time, int endTargetBackground, List<Integer> figures, Map<Integer, Integer> targets, Map<Integer, Integer> multiFigureTargetBackgound) {
		this.level = level;
		this.time = time;
		this.title = title;
		this.layout = layout;
		this.endTargetBackground = endTargetBackground;
		this.targets = targets;
		this.figures = figures;
		this.multiFigureTargetBackgound = multiFigureTargetBackgound;
	}

	public Map<Integer, Integer> getMultiFigureTargetBackgound() {
		return multiFigureTargetBackgound;
	}

	public List<Integer> getFigures() {
		return figures;
	}

	public Map<Integer, Integer> getTargets() {
		return targets;
	}

	@Override
	public long getTime() {
		return this.time;
	}

	@Override
	public int getLayout() {
		return this.layout;
	}

	public int getEndTargetBackground() {
		return endTargetBackground;
	}

	@Override
	public int getTitle() {
		return this.title;
	}

	@Override
	public GameLevel getLevel() {
		return this.level;
	}

	@Override
	public GameView getGameView(Context context) {
		return new StandardDragNDropGameView(context, this);
	}

}
